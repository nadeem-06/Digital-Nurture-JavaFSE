SET SERVEROUTPUT ON;
-- Create transactions log table
CREATE TABLE transactions (
    transaction_id   NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    account_id       NUMBER,
    transaction_type VARCHAR2(20),
    amount           NUMBER(15,2),
    transaction_date DATE DEFAULT SYSDATE,
    status           VARCHAR2(20),
    remarks          VARCHAR2(500)
);


-- Procedure 1: Deposit
CREATE OR REPLACE PROCEDURE deposit_funds(
    p_account_id IN  NUMBER,
    p_amount     IN  NUMBER,
    p_message    OUT VARCHAR2
) AS
    v_balance   NUMBER;
    v_cust_name VARCHAR2(100);
BEGIN
    -- Validate amount
    IF p_amount <= 0 THEN
        p_message := 'ERROR: Deposit amount must be positive!';
        RETURN;
    END IF;

    -- Check account exists and get balance
    SELECT customer_name, balance
    INTO   v_cust_name, v_balance
    FROM   accounts
    WHERE  account_id = p_account_id;

    -- Update balance
    UPDATE accounts
    SET    balance = balance + p_amount
    WHERE  account_id = p_account_id;

    -- Log transaction
    INSERT INTO transactions(
        account_id, transaction_type,
        amount, status, remarks
    ) VALUES (
        p_account_id, 'DEPOSIT',
        p_amount, 'SUCCESS',
        'Deposit by ' || v_cust_name
    );

    COMMIT;

    p_message := 'SUCCESS: ' || v_cust_name ||
                 ' deposited Rs.' || p_amount ||
                 '. New balance: Rs.' ||
                 (v_balance + p_amount);

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_message := 'ERROR: Account ' || p_account_id ||
                     ' not found!';
    WHEN OTHERS THEN
        ROLLBACK;
        p_message := 'ERROR: ' || SQLERRM;
END deposit_funds;
/



-- Procedure 2: Withdrawal
CREATE OR REPLACE PROCEDURE withdraw_funds(
    p_account_id IN  NUMBER,
    p_amount     IN  NUMBER,
    p_message    OUT VARCHAR2
) AS
    v_balance   NUMBER;
    v_cust_name VARCHAR2(100);
BEGIN
    -- Validate amount
    IF p_amount <= 0 THEN
        p_message := 'ERROR: Withdrawal amount must be positive!';
        RETURN;
    END IF;

    -- Get account details
    SELECT customer_name, balance
    INTO   v_cust_name, v_balance
    FROM   accounts
    WHERE  account_id = p_account_id;

    -- Check sufficient balance
    IF v_balance < p_amount THEN
        p_message := 'ERROR: Insufficient balance!' ||
                     ' Available: Rs.' || v_balance;

        -- Log failed attempt
        INSERT INTO transactions(
            account_id, transaction_type,
            amount, status, remarks
        ) VALUES (
            p_account_id, 'WITHDRAWAL',
            p_amount, 'FAILED',
            'Insufficient balance'
        );
        COMMIT;
        RETURN;
    END IF;

    -- Process withdrawal
    UPDATE accounts
    SET    balance = balance - p_amount
    WHERE  account_id = p_account_id;

    -- Log successful transaction
    INSERT INTO transactions(
        account_id, transaction_type,
        amount, status, remarks
    ) VALUES (
        p_account_id, 'WITHDRAWAL',
        p_amount, 'SUCCESS',
        'Withdrawal by ' || v_cust_name
    );

    COMMIT;

    p_message := 'SUCCESS: ' || v_cust_name ||
                 ' withdrew Rs.' || p_amount ||
                 '. Remaining: Rs.' ||
                 (v_balance - p_amount);

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_message := 'ERROR: Account not found!';
    WHEN OTHERS THEN
        ROLLBACK;
        p_message := 'ERROR: ' || SQLERRM;
END withdraw_funds;
/



-- Procedure 3: Fund Transfer
CREATE OR REPLACE PROCEDURE transfer_funds(
    p_from_id IN  NUMBER,
    p_to_id   IN  NUMBER,
    p_amount  IN  NUMBER,
    p_message OUT VARCHAR2
) AS
    v_from_balance NUMBER;
    v_from_name    VARCHAR2(100);
    v_to_name      VARCHAR2(100);
BEGIN
    -- Validate
    IF p_amount <= 0 THEN
        p_message := 'ERROR: Transfer amount must be positive!';
        RETURN;
    END IF;

    IF p_from_id = p_to_id THEN
        p_message := 'ERROR: Cannot transfer to same account!';
        RETURN;
    END IF;

    -- Get sender details
    SELECT customer_name, balance
    INTO   v_from_name, v_from_balance
    FROM   accounts
    WHERE  account_id = p_from_id;

    -- Get receiver name
    SELECT customer_name
    INTO   v_to_name
    FROM   accounts
    WHERE  account_id = p_to_id;

    -- Check balance
    IF v_from_balance < p_amount THEN
        p_message := 'ERROR: Insufficient balance in ' ||
                     v_from_name || ' account!';
        RETURN;
    END IF;

    -- Deduct from sender
    UPDATE accounts
    SET    balance = balance - p_amount
    WHERE  account_id = p_from_id;

    -- Add to receiver
    UPDATE accounts
    SET    balance = balance + p_amount
    WHERE  account_id = p_to_id;

    -- Log both sides
    INSERT INTO transactions(
        account_id, transaction_type,
        amount, status, remarks
    ) VALUES (
        p_from_id, 'TRANSFER OUT',
        p_amount, 'SUCCESS',
        'Transfer to ' || v_to_name
    );

    INSERT INTO transactions(
        account_id, transaction_type,
        amount, status, remarks
    ) VALUES (
        p_to_id, 'TRANSFER IN',
        p_amount, 'SUCCESS',
        'Transfer from ' || v_from_name
    );

    COMMIT;

    p_message := 'SUCCESS: Transferred Rs.' || p_amount ||
                 ' from ' || v_from_name ||
                 ' to '   || v_to_name;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        p_message := 'ERROR: Account not found!';
    WHEN OTHERS THEN
        ROLLBACK;
        p_message := 'ERROR: ' || SQLERRM;
END transfer_funds;
/



-- Procedure 4: Account Summary
CREATE OR REPLACE PROCEDURE get_account_summary(
    p_account_id IN NUMBER
) AS
    v_name         VARCHAR2(100);
    v_balance      NUMBER;
    v_type         VARCHAR2(20);
    v_txn_count    NUMBER;
    v_total_credit NUMBER;
    v_total_debit  NUMBER;
BEGIN
    -- Get account details
    SELECT customer_name, balance, account_type
    INTO   v_name, v_balance, v_type
    FROM   accounts
    WHERE  account_id = p_account_id;

    -- Get transaction stats
    SELECT COUNT(*),
           NVL(SUM(CASE WHEN transaction_type IN
               ('DEPOSIT','TRANSFER IN')
               THEN amount ELSE 0 END), 0),
           NVL(SUM(CASE WHEN transaction_type IN
               ('WITHDRAWAL','TRANSFER OUT')
               THEN amount ELSE 0 END), 0)
    INTO   v_txn_count, v_total_credit, v_total_debit
    FROM   transactions
    WHERE  account_id = p_account_id
    AND    status = 'SUCCESS';

    -- Print summary
    DBMS_OUTPUT.PUT_LINE('================================');
    DBMS_OUTPUT.PUT_LINE('ACCOUNT SUMMARY');
    DBMS_OUTPUT.PUT_LINE('================================');
    DBMS_OUTPUT.PUT_LINE('Account ID   : ' || p_account_id);
    DBMS_OUTPUT.PUT_LINE('Name         : ' || v_name);
    DBMS_OUTPUT.PUT_LINE('Type         : ' || v_type);
    DBMS_OUTPUT.PUT_LINE('Balance      : Rs.' || v_balance);
    DBMS_OUTPUT.PUT_LINE('Transactions : ' || v_txn_count);
    DBMS_OUTPUT.PUT_LINE('Total Credit : Rs.' || v_total_credit);
    DBMS_OUTPUT.PUT_LINE('Total Debit  : Rs.' || v_total_debit);
    DBMS_OUTPUT.PUT_LINE('================================');

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('ERROR: Account not found!');
END get_account_summary;
/




-- Test all procedures
DECLARE
    v_msg VARCHAR2(500);
BEGIN
    DBMS_OUTPUT.PUT_LINE('================================');
    DBMS_OUTPUT.PUT_LINE('TESTING ALL PROCEDURES');
    DBMS_OUTPUT.PUT_LINE('================================');

    -- Test 1: Deposit
    deposit_funds(1, 25000, v_msg);
    DBMS_OUTPUT.PUT_LINE('DEPOSIT TEST 1: ' || v_msg);

    -- Test 2: Deposit invalid amount
    deposit_funds(1, -5000, v_msg);
    DBMS_OUTPUT.PUT_LINE('DEPOSIT TEST 2: ' || v_msg);

    -- Test 3: Withdraw
    withdraw_funds(2, 10000, v_msg);
    DBMS_OUTPUT.PUT_LINE('WITHDRAW TEST 1: ' || v_msg);

    -- Test 4: Withdraw insufficient balance
    withdraw_funds(4, 50000, v_msg);
    DBMS_OUTPUT.PUT_LINE('WITHDRAW TEST 2: ' || v_msg);

    -- Test 5: Transfer
    transfer_funds(1, 2, 20000, v_msg);
    DBMS_OUTPUT.PUT_LINE('TRANSFER TEST 1: ' || v_msg);

    -- Test 6: Transfer invalid account
    transfer_funds(1, 999, 5000, v_msg);
    DBMS_OUTPUT.PUT_LINE('TRANSFER TEST 2: ' || v_msg);

    DBMS_OUTPUT.PUT_LINE('================================');
END;
/



-- View summary for all accounts
BEGIN
    DBMS_OUTPUT.PUT_LINE('ALL ACCOUNT SUMMARIES');
    FOR rec IN (SELECT account_id FROM accounts ORDER BY account_id)
    LOOP
        get_account_summary(rec.account_id);
    END LOOP;
END;
/



-- See all transactions logged
SELECT transaction_id,
       account_id,
       transaction_type,
       amount,
       status,
       remarks,
       TO_CHAR(transaction_date, 'DD-MON-YYYY HH:MI:SS') as txn_date
FROM   transactions
ORDER  BY transaction_id;
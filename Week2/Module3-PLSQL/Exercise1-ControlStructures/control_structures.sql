SET SERVEROUTPUT ON;
-- Create accounts table
CREATE TABLE accounts (
    account_id    NUMBER PRIMARY KEY,
    customer_name VARCHAR2(100),
    balance       NUMBER(15,2),
    account_type  VARCHAR2(20)
);

-- Insert sample data
INSERT INTO accounts VALUES (1, 'Nadeem',  150000.00, 'SAVINGS');
INSERT INTO accounts VALUES (2, 'Alice',    45000.00, 'SAVINGS');
INSERT INTO accounts VALUES (3, 'Bob',     200000.00, 'CURRENT');
INSERT INTO accounts VALUES (4, 'Charlie',   8000.00, 'SAVINGS');
INSERT INTO accounts VALUES (5, 'David',   500000.00, 'PREMIUM');

COMMIT;

-- Verify data
SELECT * FROM accounts;


-- Block 1: IF-THEN-ELSIF-ELSE
-- Check loan eligibility based on account balance
DECLARE
    v_account_id  NUMBER        := 1;
    v_balance     NUMBER;
    v_cust_name   VARCHAR2(100);
    v_eligible    VARCHAR2(5);
    v_loan_limit  NUMBER;
    v_interest    NUMBER;
BEGIN
    -- Get customer details
    SELECT customer_name, balance
    INTO   v_cust_name, v_balance
    FROM   accounts
    WHERE  account_id = v_account_id;

    -- Check eligibility using IF-ELSIF-ELSE
    IF v_balance >= 200000 THEN
        v_eligible   := 'YES';
        v_loan_limit := v_balance * 0.80;
        v_interest   := 8.5;

    ELSIF v_balance >= 100000 THEN
        v_eligible   := 'YES';
        v_loan_limit := v_balance * 0.60;
        v_interest   := 10.0;

    ELSIF v_balance >= 50000 THEN
        v_eligible   := 'YES';
        v_loan_limit := v_balance * 0.40;
        v_interest   := 12.0;

    ELSE
        v_eligible   := 'NO';
        v_loan_limit := 0;
        v_interest   := 0;
    END IF;

    -- Print results
    DBMS_OUTPUT.PUT_LINE('================================');
    DBMS_OUTPUT.PUT_LINE('LOAN ELIGIBILITY REPORT');
    DBMS_OUTPUT.PUT_LINE('================================');
    DBMS_OUTPUT.PUT_LINE('Customer     : ' || v_cust_name);
    DBMS_OUTPUT.PUT_LINE('Balance      : ' || v_balance);
    DBMS_OUTPUT.PUT_LINE('Eligible     : ' || v_eligible);

    IF v_eligible = 'YES' THEN
        DBMS_OUTPUT.PUT_LINE('Loan Limit   : ' || v_loan_limit);
        DBMS_OUTPUT.PUT_LINE('Interest Rate: ' || v_interest || '%');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Reason: Insufficient balance');
    END IF;

    DBMS_OUTPUT.PUT_LINE('================================');

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: Account not found!');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/


-- Block 2: CASE Statement
-- Categorize all accounts based on balance
DECLARE
    v_category VARCHAR2(20);
    v_message  VARCHAR2(200);
BEGIN
    DBMS_OUTPUT.PUT_LINE('================================');
    DBMS_OUTPUT.PUT_LINE('ACCOUNT CATEGORY REPORT');
    DBMS_OUTPUT.PUT_LINE('================================');

    -- Loop through all accounts
    FOR rec IN (SELECT account_id, customer_name, balance
                FROM   accounts
                ORDER  BY account_id)
    LOOP
        -- CASE based on balance range
        CASE
            WHEN rec.balance >= 400000 THEN
                v_category := 'PLATINUM';
                v_message  := 'Exclusive benefits apply';

            WHEN rec.balance >= 200000 THEN
                v_category := 'GOLD';
                v_message  := 'Priority banking enabled';

            WHEN rec.balance >= 100000 THEN
                v_category := 'SILVER';
                v_message  := 'Standard benefits apply';

            WHEN rec.balance >= 25000 THEN
                v_category := 'BRONZE';
                v_message  := 'Basic banking services';

            ELSE
                v_category := 'BASIC';
                v_message  := 'Minimum balance warning';
        END CASE;

        DBMS_OUTPUT.PUT_LINE(
            rec.customer_name ||
            ' | Balance: ' || rec.balance ||
            ' | Category: ' || v_category ||
            ' | ' || v_message
        );
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('================================');
END;
/

-- Block 3: Basic LOOP with EXIT WHEN
-- Calculate compound interest for 5 years
DECLARE
    v_principal   NUMBER := 100000;
    v_rate        NUMBER := 0.08;
    v_year        NUMBER := 1;
    v_amount      NUMBER := 100000;
    v_interest    NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('================================');
    DBMS_OUTPUT.PUT_LINE('COMPOUND INTEREST CALCULATOR');
    DBMS_OUTPUT.PUT_LINE('Principal: ' || v_principal);
    DBMS_OUTPUT.PUT_LINE('Rate: 8% per year');
    DBMS_OUTPUT.PUT_LINE('================================');

    LOOP
        -- Calculate interest for this year
        v_interest := v_amount * v_rate;
        v_amount   := v_amount + v_interest;

        DBMS_OUTPUT.PUT_LINE(
            'Year ' || v_year ||
            ' | Interest: ' || ROUND(v_interest, 2) ||
            ' | Total: '    || ROUND(v_amount, 2)
        );

        v_year := v_year + 1;

        -- Exit after 5 years
        EXIT WHEN v_year > 5;
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('================================');
    DBMS_OUTPUT.PUT_LINE(
        'Final Amount after 5 years: ' || ROUND(v_amount, 2)
    );
END;
/


-- Block 4: WHILE LOOP
-- Apply monthly interest to all savings accounts
DECLARE
    v_month      NUMBER := 1;
    v_total_paid NUMBER := 0;
    v_monthly_rate NUMBER := 0.005; -- 0.5% per month = 6% annual
BEGIN
    DBMS_OUTPUT.PUT_LINE('================================');
    DBMS_OUTPUT.PUT_LINE('MONTHLY INTEREST APPLICATION');
    DBMS_OUTPUT.PUT_LINE('================================');

    WHILE v_month <= 6 LOOP  -- Apply for 6 months

        -- Update all savings accounts
        UPDATE accounts
        SET    balance = balance + (balance * v_monthly_rate)
        WHERE  account_type = 'SAVINGS';

        DBMS_OUTPUT.PUT_LINE(
            'Month ' || v_month ||
            ': Interest applied to all SAVINGS accounts'
        );

        v_month := v_month + 1;
    END LOOP;

    COMMIT;

    -- Show updated balances
    DBMS_OUTPUT.PUT_LINE('================================');
    DBMS_OUTPUT.PUT_LINE('UPDATED SAVINGS BALANCES:');
    FOR rec IN (SELECT customer_name, balance
                FROM   accounts
                WHERE  account_type = 'SAVINGS')
    LOOP
        DBMS_OUTPUT.PUT_LINE(
            rec.customer_name || ': ' || ROUND(rec.balance, 2)
        );
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('================================');
END;
/


-- Block 5: FOR LOOP
-- Generate account statements for all customers
DECLARE
    v_total_balance NUMBER := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE('================================');
    DBMS_OUTPUT.PUT_LINE('ACCOUNT STATEMENT SUMMARY');
    DBMS_OUTPUT.PUT_LINE('================================');

    -- FOR loop with cursor
    FOR rec IN (SELECT account_id,
                       customer_name,
                       balance,
                       account_type
                FROM   accounts
                ORDER  BY account_id)
    LOOP
        DBMS_OUTPUT.PUT_LINE(
            'ID: '   || rec.account_id    ||
            ' | '    || rec.customer_name ||
            ' | Rs.' || rec.balance       ||
            ' | '    || rec.account_type
        );
        v_total_balance := v_total_balance + rec.balance;
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('================================');
    DBMS_OUTPUT.PUT_LINE(
        'TOTAL BANK BALANCE: Rs.' || v_total_balance
    );
    DBMS_OUTPUT.PUT_LINE('================================');
END;
/
application propertities: added DB Details, jpa ddl-auto:update, show-sql kept true.
Added dependencies : H2 and MySQL database, Lombok, Spring data Jpa and spring web.
Bank Application: Controllers, Entity, Repository, Service, Exception need to add.
Need to Add test cases.
Flow Chart of Banking application:
1. start
2. Customer Onboarding (capture KYC, Save to DB)
3. check if minor. if yes, require joint account (future), else proceed normall process.
4. Open Savings Account ( Set Balance, minimum balance)
5. Open Loan Account ( Capture Details, calculate EMI)
6. Handle Transactions: Deposit, WithDraw, Pay EMI
7. For WithDrawals, check min balance. If it is below, apply penalty
8. For EMI payments, check if on time pays or not otherwise confider as late, apply fee after 5 or some days.
9. End

# SQL Validation Examples

These examples demonstrate QA data-validation skills. Adapt table and column names to the actual database schema when database access is available.

```sql
SELECT employee_id, COUNT(*)
FROM employees
GROUP BY employee_id
HAVING COUNT(*) > 1;

SELECT *
FROM users
WHERE username IS NULL OR TRIM(username) = '';

SELECT COUNT(*)
FROM users
WHERE status = 'Active';

SELECT employee_id, first_name, last_name
FROM employees
WHERE employee_id = 'EMP001';
```

These are portfolio examples, not evidence of direct access to the OrangeHRM demo database.

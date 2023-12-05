#Both query will give same result ,so without entering password ican logged
SELECT * FROM sqlInjection.user WHERE user_name = 'manish' AND password = 'Manish@123';
SELECT * FROM sqlInjection.user WHERE user_name = 'manish' AND ( password = '' or '1'='1');
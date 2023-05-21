--1.Знайти усі апартаменти які знаходяться в обраному статусі на проміжку обраного часу.
SELECT DISTINCT *
FROM apartment a LEFT JOIN apartment_status as2
ON a.id = as2.apartment_id AND (as2.arrival_day <= ? AND as2.end_day >= ?)
       AND (as2.pay_time_limit IS NULL OR as2.pay_time_limit >=NOW())
WHERE (as2.status = ?)

--2.Знайти усі апартаменти оплачені користувачем К1.
SELECT DISTINCT *
FROM apartment a
LEFT JOIN apartment_status as2 ON a.id = as2.apartment_id
AND as.status = 'BOUGHT' LEFT JOIN usr u on as2.user_id = u.user_id AND u.user_name = ?

--3.Обрати користувачів що мають бронювання на усі номери.
SELECT u.username
FROM usr u
WHERE u.id  IN
      (SELECT as2.user_id
       FROM apartment_status as2
       GROUP BY as2.user_id
       HAVING COUNT(as2.apartment_id)
                  =
              (SELECT COUNT(a.id)
               FROM apartment a)
      );



--4.Обрати номери в яких є будуть зупиняться прийнамні усі люди що зупиняються в номері Н1.
SELECT a.id
FROM apartment a
WHERE NOT EXISTS (
        SELECT as2.user_id
        FROM apartment_status as2
        WHERE as2.apartment_id = ?
          AND as2.user_id NOT IN (
            SELECT as3.user_id
            FROM apartment_status as3
            WHERE as3.apartment_id = a.id
        )
    )

--5.Обрати усіх користувачів що мають ті і тільки ті ролі що і користувач К1
SELECT *
FROM usr u
WHERE NOT EXISTS (
        SELECT ur.role_id
        FROM user_role ur
        WHERE ur.user_id = u.id AND ur.role_id NOT IN (
            SELECT ur2.role_id
            FROM user_role ur2 JOIN usr u2 ON ur2.user_id = u2.id
            WHERE u2.username = ?
        )
    )
AND (SELECT COUNT(*) FROM user_role ur3 WHERE ur3.user_id = u.id) =
    (SELECT COUNT(*) FROM user_role ur3 JOIN usr u4 ON ur3.user_id = u4.id  WHERE u4.username  = ?)
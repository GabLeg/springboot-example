INSERT INTO BREWERY (ID, NAME, ADDRESS, PHONE)
VALUES (1, 'BREWERY_TEST', '1234 Castle', '410-001-1234');
INSERT INTO BREW_MASTER (ID, NAME, EXPERIENCE)
values (1, 'BREW MASTER', 'MASTER');
INSERT INTO BEER (ID, NAME, COLOR, IBU, ALCOHOL, PRICE, FK_BREWERY, FK_BREW_MASTER)
VALUES (1, 'RED ALE', 'RED', 36, 4.5, 7.25, 1, 1);
INSERT INTO INVOICE (ID, DATE, TOTAL)
values (1, '2019-01-01', 14.50);
INSERT INTO PURCHASE_ITEM (ID, QUANTITY, FK_BEER, FK_INVOICE)
VALUES (1, 2, 1, 1);
INSERT INTO GOODY (ID, NAME, DESCRIPTION)
VALUES (1, 'CANDIES', 'A bad of candies');


INSERT INTO category (id, name, description)
VALUES
    (1, 'Elektronika', 'Bardzo fajne przedmioty'),
    (2, 'Moto', 'Samochody, skutery i ciężarówki'),
    (3, 'Meble', 'Wyposażenie wnętrza');

INSERT INTO offer (id, title, description, img_url, price, category_id)
VALUES
    (1, 'Telewizor', 'Super telewizor o przekątnej 55 cali', 'http://blabla.jpg', 1299, 1),
    (2, 'Kino domowe', 'Wypasione kino domowe firmy Sony, gra tak, że można robić festyn', 'http://blabla2.jpg', 699, 1),
    (3, 'Stół drewniany', 'Idealny stół drewniany dla rodziny, 6 krzeseł gratis', 'http://blabla3.jpg', 2699, 3);

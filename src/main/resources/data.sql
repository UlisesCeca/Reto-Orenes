INSERT INTO locations (id, date_time, latitude, longitude) VALUES (1, '2022-10-02 13:30:02', 1234566, 1234567);

INSERT INTO vehicles (id, plate_number, last_location_id) VALUES (1, '111111A', 1);
INSERT INTO vehicles (id, plate_number) VALUES (2, '111111B');
INSERT INTO vehicles (id, plate_number) VALUES (3, '111111C');
INSERT INTO vehicles (id, plate_number) VALUES (4, '111111D');

INSERT INTO orders (id, order_id, assigned_vehicle_id) VALUES (1, 1, 1);
INSERT INTO orders (id, order_id, assigned_vehicle_id) VALUES (2, 2, 2);
INSERT INTO orders (id, order_id, assigned_vehicle_id) VALUES (3, 3, 3);
INSERT INTO orders (id, order_id, assigned_vehicle_id) VALUES (4, 4, 4);
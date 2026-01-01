INSERT INTO `gpsfleet`.`fleet`
(`fleet_id`, `name`, `owner_id`, `created_at`)
VALUES
    (1, 'Alpha Logistics Fleet', 101, CURRENT_TIMESTAMP),
    (2, 'CityCab Fleet', 102, CURRENT_TIMESTAMP);

INSERT INTO `gpsfleet`.`driver`
(`driver_id`, `name`, `phone`, `license_no`, `created_at`)
VALUES
    (1, 'Ramesh Patel', '9876543210', 'GJ05-2023-1123', CURRENT_TIMESTAMP),
    (2, 'Sonal Mehta', '9825012345', 'GJ01-2022-9981', CURRENT_TIMESTAMP),
    (3, 'Imran Shaikh', '9898123456', 'GJ18-2021-5532', CURRENT_TIMESTAMP);

INSERT INTO `gpsfleet`.`vehicle`
(`vehicle_id`, `fleet_id`, `driver_id`, `reg_no`, `model`, `active`, `created_at`)
VALUES
    (1, 1, 1, 'GJ05 AB 1123', 'Tata Ace', 1, CURRENT_TIMESTAMP),
    (2, 1, 2, 'GJ05 XY 8899', 'Mahindra Bolero', 1, CURRENT_TIMESTAMP),
    (3, 2, 3, 'GJ01 MN 4521', 'Toyota Innova', 1, CURRENT_TIMESTAMP);

INSERT INTO `gpsfleet`.`device`
(`device_id`, `imei`, `vehicle_id`, `active`, `created_at`)
VALUES
    (1, '359874560123456', 1, 1, CURRENT_TIMESTAMP),
    (2, '864589123654789', 2, 1, CURRENT_TIMESTAMP),
    (3, '352698745120369', 3, 1, CURRENT_TIMESTAMP);

INSERT INTO `gpsfleet`.`geofence`
(`id`, `center_lat`, `center_lon`, `created_at`, `name`, `radius_meters`, `fleet_id`)
VALUES
    (1, 21.1702, 72.8311, CURRENT_TIMESTAMP, 'Surat Warehouse', 300, 1),
    (2, 21.1938, 72.8515, CURRENT_TIMESTAMP, 'Adajan Delivery Zone', 500, 1),
    (3, 23.0225, 72.5714, CURRENT_TIMESTAMP, 'Ahmedabad Hub', 400, 2);


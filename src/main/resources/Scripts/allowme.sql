CREATE DATABASE "challenge-db";
CREATE USER allowme WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE "challenge-db" to allowme;

CREATE TABLE IF NOT EXISTS services (
  id INT NOT NULL,
  name TEXT NOT NULL,
  endpoint TEXT NOT NULL,
  path TEXT NOT NULL,
  price_per_request NUMERIC(10,6) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO services (id, name, endpoint, path, price_per_request) VALUES 
    (1, 'Weather - API', 'https://weather.contrateumdev.com.br/api', '/weather/city', 0.95035),
    (2, 'Geolocalização - API', 'https://geolocation.contrateumdev.com.br/api', '/geocode', 19.1203);

CREATE TABLE IF NOT EXISTS service_requests (
    id  SERIAL NOT NULL,
    created_at  TIMESTAMPTZ,
    service_id  INT NOT NULL,
    status_code INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_sr_service_id FOREIGN KEY(service_id) REFERENCES services(id)
);

CREATE TABLE IF NOT EXISTS billings (
    id  SERIAL NOT NULL,
    created_at  TIMESTAMPTZ,
    start_date  DATE,
    end_date    DATE,
    total_price NUMERIC(10,6) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS billing_summary  (
    id  SERIAL NOT NULL,
    created_at  TIMESTAMPTZ,
    billing_id  INT NOT NULL,
    service_id  INT NOT NULL,
    total_requests  INT NOT NULL,
    price_per_request NUMERIC(10,6) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_bs_billing_id FOREIGN KEY(billing_id) REFERENCES billings(id),
    CONSTRAINT fk_bs_service_id FOREIGN KEY(service_id) REFERENCES services(id)
);
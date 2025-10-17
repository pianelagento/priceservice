DROP TABLE IF EXISTS PRICES;

CREATE TABLE PRICES (
  id IDENTITY PRIMARY KEY,
  brand_id BIGINT NOT NULL,
  start_date TIMESTAMP NOT NULL,
  end_date TIMESTAMP NOT NULL,
  price_list INT NOT NULL,
  product_id BIGINT NOT NULL,
  priority INT NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  curr VARCHAR(3) NOT NULL
);

CREATE INDEX idx_prices_lookup
  ON PRICES (brand_id, product_id, start_date, end_date, priority);
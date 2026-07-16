--liquibase formatted sql

--changeset pak:create_auto_insurance_policy_table
CREATE TABLE auto_insurance_policies (
                                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                                       policy_number VARCHAR(50) NOT NULL UNIQUE,
                                       client_external_id UUID NOT NULL,

                                       start_date DATE NOT NULL,
                                       end_date DATE NOT NULL,

                                       status VARCHAR(20) NOT NULL CHECK (
                                           status IN ('DRAFT', 'ACTIVE', 'EXPIRED', 'CANCELLED', 'PENDING_PAYMENT', 'SUSPENDED')
                                           ),

                                       premium_amount DECIMAL(15,2),
                                       coverage_amount DECIMAL(15,2),

                                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                       vehicle_vin VARCHAR(50),
                                       vehicle_model VARCHAR(100),
                                       vehicle_year INTEGER,

                                       driver_experience_years INTEGER CHECK (driver_experience_years >= 0),
                                       has_accident_history BOOLEAN,

                                       engine_power_hp INTEGER CHECK (engine_power_hp >= 1),
                                       driver_age INTEGER,

                                       driver_license_category VARCHAR(20),

                                       CONSTRAINT chk_auto_policy_dates CHECK (end_date >= start_date)
);
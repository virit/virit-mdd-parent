-- Feature 特性
CREATE TABLE IF NOT EXISTS base_feature
(

    `id` VARCHAR
(
    64
) PRIMARY KEY,
    `created_user_id` VARCHAR
(
    32
),
    `modified_user_id` VARCHAR
(
    32
),
    `created_time` DATETIME,
    `modified_time` DATETIME,
    `name` VARCHAR
(
    32
),
    `schema_data` TEXT
    );
$$$
-- Model 模型
CREATE TABLE IF NOT EXISTS base_model
(

    `id` VARCHAR
(
    32
) PRIMARY KEY,
    `created_user_id` VARCHAR
(
    32
),
    `modified_user_id` VARCHAR
(
    32
),
    `created_time` DATETIME,
    `modified_time` DATETIME,
    `name` VARCHAR
(
    32
),
    `domain` VARCHAR
(
    32
),
    `schema_data` TEXT,
    `mapper_class` VARCHAR
(
    64
),
    `service_class` VARCHAR
(
    64
),
    `repo_class` VARCHAR
(
    64
)
    );
$$$

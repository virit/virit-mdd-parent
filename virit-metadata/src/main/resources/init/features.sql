REPLACE
INTO base_feature (id, schema_data) VALUES ('BASE', 'fields:
  id:
    title: ID
    type: STRING
    properties: 
      length: 32
      primaryKey: True
  createdUserId:
    title: 创建人
    type: STRING
    properties: 
      length: 32
  updatedUserId:
    title: 修改人
    type: STRING
    properties: 
      length: 32
  createdTime:
    title: 创建时间
    type: DATE
    properties: 
      length: 32
  modifiedTime:
    title: 修改时间
    type: DATE
    properties: 
      length: 32
adapter: cn.virit.common.feature.FeatureBase
superTypes: 
  - cn.virit.common.base.BaseEntity
');$$$
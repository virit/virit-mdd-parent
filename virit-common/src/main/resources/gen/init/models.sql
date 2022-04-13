DELETE
FROM base_model
WHERE id = 'base.Feature';
$$$
INSERT INTO base_model (id, domain, name, schema_data, mapper_class, repo_class, service_class) VALUES ('base.Feature', 'base', 'Feature', '!!cn.virit.metadata.model.ModelInfo
adapter: null
author: null
children: null
codeLevels: null
content: null
description: 特性
domain: base
feature: true
features: [!!cn.virit.metadata.constants.FeatureName ''BASE'']
fields:
  id:
    name: id
    properties: {length: 64, primaryKey: true}
    title: ID
    type: STRING
  createdUserId:
    name: createdUserId
    properties: {length: 32}
    title: 创建人
    type: STRING
  modifiedUserId:
    name: modifiedUserId
    properties: {length: 32}
    title: 修改人
    type: STRING
  createdTime:
    name: createdTime
    properties: {length: 32}
    title: 创建时间
    type: DATE
  modifiedTime:
    name: modifiedTime
    properties: {length: 32}
    title: 修改时间
    type: DATE
  name:
    name: name
    properties: {length: 32}
    title: 名称
    type: STRING
  schemaData:
    name: schemaData
    properties: {length: 1024}
    title: 特性定义
    type: STRING
name: Feature
superTypes: !!set {cn.virit.common.base.BaseEntity: null}
title: 特性
', 'cn.virit.domain.base.gen.mapper.FeatureMapper', 'cn.virit.domain.base.repo.FeatureRepository', 'cn.virit.domain.base.service.FeatureService');$$$
DELETE
FROM base_model
WHERE id = 'base.Model';
$$$
INSERT INTO base_model (id, domain, name, schema_data, mapper_class, repo_class, service_class) VALUES ('base.Model', 'base', 'Model', '!!cn.virit.metadata.model.ModelInfo
adapter: null
author: null
children: null
codeLevels: null
content: null
description: 模型
domain: base
feature: true
features: [!!cn.virit.metadata.constants.FeatureName ''BASE'']
fields:
  id:
    name: id
    properties: {length: 32, primaryKey: true}
    title: ID
    type: STRING
  createdUserId:
    name: createdUserId
    properties: {length: 32}
    title: 创建人
    type: STRING
  modifiedUserId:
    name: modifiedUserId
    properties: {length: 32}
    title: 修改人
    type: STRING
  createdTime:
    name: createdTime
    properties: {length: 32}
    title: 创建时间
    type: DATE
  modifiedTime:
    name: modifiedTime
    properties: {length: 32}
    title: 修改时间
    type: DATE
  name:
    name: name
    properties: {length: 32}
    title: 名称
    type: STRING
  domain:
    name: domain
    properties: {length: 32}
    title: 领域
    type: STRING
  schemaData:
    name: schemaData
    properties: {length: 1024}
    title: 模型定义
    type: STRING
  mapperClass:
    name: mapperClass
    properties: {length: 64}
    title: mapperClass
    type: STRING
  serviceClass:
    name: serviceClass
    properties: {length: 64}
    title: serverClass
    type: STRING
  repoClass:
    name: repoClass
    properties: {length: 64}
    title: repoClass
    type: STRING
name: Model
superTypes: !!set {cn.virit.common.base.BaseEntity: null}
title: 模型
', 'cn.virit.domain.base.gen.mapper.ModelMapper', 'cn.virit.domain.base.repo.ModelRepository', 'cn.virit.domain.base.service.ModelService');$$$

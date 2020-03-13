
DROP TABLE IF EXISTS `device_status`;
CREATE TABLE `device_status`
(
  `id`              INT         NOT NULL AUTO_INCREMENT COMMENT '主键自增' PRIMARY KEY,
  `imei`            VARCHAR(20) NOT NULL COMMENT '轨迹锁IMEI', 
  `device_id`       VARCHAR(40) NOT NULL COMMENT '轨迹锁编号', 
  `battery`          INT COMMENT '电量', 
  `battery_time`     DATETIME COMMENT '电量更新时间',
  `lock_state`          INT COMMENT '锁状态', 
  `lock_time`     DATETIME COMMENT '锁状态更新时间',
  `state_interval`     INT COMMENT '状态上传间隔', 
  `position_interval`   INT COMMENT '定位上传间隔' 
) COMMENT '轨迹锁状态表';

DROP TABLE IF EXISTS `device_unlock_info`;
CREATE TABLE `device_unlock_info`
(
  `id`           INT NOT NULL AUTO_INCREMENT COMMENT '主键自增' PRIMARY KEY,
  `imei`         VARCHAR(20) COMMENT '轨迹锁IMEI',
  `lock_state`          INT COMMENT '锁状态类型', 
  `create_time`     DATETIME COMMENT '创建时间',
  `position_code`  VARCHAR(10) COMMENT '定位编号'
) COMMENT '轨迹锁开锁记录';

DROP TABLE IF EXISTS `device_task`;
CREATE TABLE `device_task`
(
  `id`           INT NOT NULL AUTO_INCREMENT COMMENT '主键自增' PRIMARY KEY,
  `imei`         VARCHAR(20) COMMENT '轨迹锁IMEI',
  `device_id`    VARCHAR(40) NOT NULL COMMENT '轨迹锁编号', 
  `service_id`    VARCHAR(20) COMMENT '服务ID',
  `command_id`    VARCHAR(20) COMMENT '命令ID',
  `method`       VARCHAR(20) COMMENT '指令',
  `data`         VARCHAR(100) COMMENT '设置值',
  `create_time`  DATETIME COMMENT '创建时间',
  `assign_time`  DATETIME COMMENT '下达时间',
  `execute_time` DATETIME COMMENT '执行时间',
  `success_time` DATETIME COMMENT '执行成功时间',
  `task_status`  VARCHAR(20) COMMENT '任务状态',
  `result_detail`  VARCHAR(100) COMMENT '详细信息'
) COMMENT '轨迹锁任务';


DROP TABLE IF EXISTS `position_info`;
CREATE TABLE `position_info` (
  `id`           INT NOT NULL AUTO_INCREMENT COMMENT '主键自增' PRIMARY KEY,
  `imei`         VARCHAR(20) COMMENT '轨迹锁IMEI',
  `device_id`    VARCHAR(40) NOT NULL COMMENT '轨迹锁编号', 
  `longitude`   VARCHAR(255) COMMENT '经度',
  `latitude`    VARCHAR(255) COMMENT '纬度',
  `position_type`  INT COMMENT '定位类型',
  `position_code`  VARCHAR(10) COMMENT '定位编号',
  `record_time` DATETIME COMMENT '记录时间',
  `create_time`     DATETIME COMMENT '创建时间'
) COMMENT '轨迹锁位置';


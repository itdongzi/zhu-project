import request from '@/utils/request'

// 查询实验室预约列表
export function listRoomorders(data) {
  return request({
    url: '/labsys/roomorders/list',
    method: 'post',
    data: data
  })
}

// 查询实验室预约详细
export function getRoomorders(id) {
  return request({
    url: '/labsys/roomorders/' + id,
    method: 'get'
  })
}

// 新增实验室预约
export function addRoomorders(data) {
  return request({
    url: '/labsys/roomorders',
    method: 'post',
    data: data
  })
}

// 修改实验室预约
export function uptRoomorders(data) {
  return request({
    url: '/labsys/roomorders',
    method: 'put',
    data: data
  })
}

// 删除实验室预约
export function delRoomorders(ids) {
  return request({
    url: '/labsys/roomorders/' + ids,
    method: 'delete'
  })
}

// 导出实验室预约
export function exptRoomorders(data) {
  return request({
    url: '/labsys/roomorders/export',
    method: 'post',
    data: data
  })
}
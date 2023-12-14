import request from '@/utils/request'

// 查询实验室信息列表
export function listRoominfo(data) {
  return request({
    url: '/labsys/roominfo/list',
    method: 'post',
    data: data
  })
}

export function queryRoominfo(data) {
  return request({
    url: '/labsys/roominfo/query',
    method: 'post',
    data: data
  })
}

// 查询实验室信息详细
export function getRoominfo(id) {
  return request({
    url: '/labsys/roominfo/' + id,
    method: 'get'
  })
}

// 新增实验室信息
export function addRoominfo(data) {
  return request({
    url: '/labsys/roominfo',
    method: 'post',
    data: data
  })
}

// 修改实验室信息
export function uptRoominfo(data) {
  return request({
    url: '/labsys/roominfo',
    method: 'put',
    data: data
  })
}

// 删除实验室信息
export function delRoominfo(ids) {
  return request({
    url: '/labsys/roominfo/' + ids,
    method: 'delete'
  })
}

// 导出实验室信息
export function exptRoominfo(data) {
  return request({
    url: '/labsys/roominfo/export',
    method: 'post',
    data: data
  })
}

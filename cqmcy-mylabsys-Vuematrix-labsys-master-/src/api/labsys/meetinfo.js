import request from '@/utils/request'

// 查询实验室信息列表
export function listMeetinfo(data) {
  return request({
    url: '/labsys/meetinfo/list',
    method: 'post',
    data: data
  })
}

export function queryMeetinfo(data) {
  return request({
    url: '/labsys/meetinfo/query',
    method: 'post',
    data: data
  })
}

// 查询实验室信息详细
export function getMeetinfo(id) {
  return request({
    url: '/labsys/meetinfo/' + id,
    method: 'get'
  })
}

// 新增实验室信息
export function addMeetinfo(data) {
  return request({
    url: '/labsys/meetinfo',
    method: 'post',
    data: data
  })
}

// 修改实验室信息
export function uptMeetinfo(data) {
  return request({
    url: '/labsys/meetinfo',
    method: 'put',
    data: data
  })
}

// 删除实验室信息
export function delMeetinfo(ids) {
  return request({
    url: '/labsys/meetinfo/' + ids,
    method: 'delete'
  })
}

// 导出实验室信息
export function exptMeetinfo(data) {
  return request({
    url: '/labsys/meetinfo/export',
    method: 'post',
    data: data
  })
}

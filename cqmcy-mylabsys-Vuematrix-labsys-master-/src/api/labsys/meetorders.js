import request from '@/utils/request'

// 查询实验室预约列表
export function listMeetorders(data) {
  return request({
    url: '/labsys/meetorders/list',
    method: 'post',
    data: data
  })
}

// 查询实验室预约详细
export function getMeetorders(id) {
  return request({
    url: '/labsys/meetorders/' + id,
    method: 'get'
  })
}

// 新增实验室预约
export function addMeetorders(data) {
  return request({
    url: '/labsys/meetorders',
    method: 'post',
    data: data
  })
}

// 修改实验室预约
export function uptMeetorders(data) {
  return request({
    url: '/labsys/meetorders',
    method: 'put',
    data: data
  })
}

// 删除实验室预约
export function delMeetorders(ids) {
  return request({
    url: '/labsys/meetorders/' + ids,
    method: 'delete'
  })
}

// 导出实验室预约
export function exptMeetorders(data) {
  return request({
    url: '/labsys/meetorders/export',
    method: 'post',
    data: data
  })
}
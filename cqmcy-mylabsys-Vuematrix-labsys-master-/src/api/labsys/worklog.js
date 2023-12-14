import request from '@/utils/request'

// 查询实训室工作/检查记录列表
export function listWorklog(data) {
  return request({
    url: '/labsys/worklog/list',
    method: 'post',
    data: data
  })
}

// 查询实训室工作/检查记录详细
export function getWorklog(id) {
  return request({
    url: '/labsys/worklog/' + id,
    method: 'get'
  })
}

// 新增实训室工作/检查记录
export function addWorklog(data) {
  return request({
    url: '/labsys/worklog',
    method: 'post',
    data: data
  })
}

// 修改实训室工作/检查记录
export function uptWorklog(data) {
  return request({
    url: '/labsys/worklog',
    method: 'put',
    data: data
  })
}

// 删除实训室工作/检查记录
export function delWorklog(ids) {
  return request({
    url: '/labsys/worklog/' + ids,
    method: 'delete'
  })
}

// 导出实训室工作/检查记录
export function exptWorklog(data) {
  return request({
    url: '/labsys/worklog/export',
    method: 'post',
    data: data
  })
}
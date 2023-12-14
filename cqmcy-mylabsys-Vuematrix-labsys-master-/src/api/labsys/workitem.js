import request from '@/utils/request'

// 查询实训室工作/检查项目列表
export function listWorkitem(data) {
  return request({
    url: '/labsys/workitem/list',
    method: 'post',
    data: data
  })
}

// 查询实训室工作/检查项目详细
export function getWorkitem(id) {
  return request({
    url: '/labsys/workitem/' + id,
    method: 'get'
  })
}

// 新增实训室工作/检查项目
export function addWorkitem(data) {
  return request({
    url: '/labsys/workitem',
    method: 'post',
    data: data
  })
}

// 修改实训室工作/检查项目
export function uptWorkitem(data) {
  return request({
    url: '/labsys/workitem',
    method: 'put',
    data: data
  })
}

// 删除实训室工作/检查项目
export function delWorkitem(ids) {
  return request({
    url: '/labsys/workitem/' + ids,
    method: 'delete'
  })
}

// 导出实训室工作/检查项目
export function exptWorkitem(data) {
  return request({
    url: '/labsys/workitem/export',
    method: 'post',
    data: data
  })
}
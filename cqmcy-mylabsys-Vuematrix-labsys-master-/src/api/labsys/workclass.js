import request from '@/utils/request'

// 查询实验室检查类型列表
export function listWorkclass(data) {
  return request({
    url: '/labsys/workclass/list',
    method: 'post',
    data: data
  })
}

// 查询实验室检查类型详细
export function getWorkclass(id) {
  return request({
    url: '/labsys/workclass/' + id,
    method: 'get'
  })
}

// 新增实验室检查类型
export function addWorkclass(data) {
  return request({
    url: '/labsys/workclass',
    method: 'post',
    data: data
  })
}

// 修改实验室检查类型
export function uptWorkclass(data) {
  return request({
    url: '/labsys/workclass',
    method: 'put',
    data: data
  })
}

// 删除实验室检查类型
export function delWorkclass(ids) {
  return request({
    url: '/labsys/workclass/' + ids,
    method: 'delete'
  })
}

// 导出实验室检查类型
export function exptWorkclass(data) {
  return request({
    url: '/labsys/workclass/export',
    method: 'post',
    data: data
  })
}
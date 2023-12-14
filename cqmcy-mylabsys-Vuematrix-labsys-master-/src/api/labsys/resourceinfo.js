import request from '@/utils/request'

// 查询教学资源信息列表
export function listResourceinfo(data) {
  return request({
    url: '/labsys/resourceinfo/list',
    method: 'post',
    data: data
  })
}

// 查询教学资源信息详细
export function getResourceinfo(id) {
  return request({
    url: '/labsys/resourceinfo/' + id,
    method: 'get'
  })
}

// 新增教学资源信息
export function addResourceinfo(data) {
  return request({
    url: '/labsys/resourceinfo',
    method: 'post',
    data: data
  })
}

// 修改教学资源信息
export function uptResourceinfo(data) {
  return request({
    url: '/labsys/resourceinfo',
    method: 'put',
    data: data
  })
}

// 删除教学资源信息
export function delResourceinfo(ids) {
  return request({
    url: '/labsys/resourceinfo/' + ids,
    method: 'delete'
  })
}

// 导出教学资源信息
export function exptResourceinfo(data) {
  return request({
    url: '/labsys/resourceinfo/export',
    method: 'post',
    data: data
  })
}
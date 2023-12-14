import request from '@/utils/request'

// 查询教学资源分类列表
export function listResourceclass(data) {
  return request({
    url: '/labsys/resourceclass/list',
    method: 'post',
    data: data
  })
}

// 查询教学资源分类详细
export function getResourceclass(id) {
  return request({
    url: '/labsys/resourceclass/' + id,
    method: 'get'
  })
}

// 新增教学资源分类
export function addResourceclass(data) {
  return request({
    url: '/labsys/resourceclass',
    method: 'post',
    data: data
  })
}

// 修改教学资源分类
export function uptResourceclass(data) {
  return request({
    url: '/labsys/resourceclass',
    method: 'put',
    data: data
  })
}

// 删除教学资源分类
export function delResourceclass(ids) {
  return request({
    url: '/labsys/resourceclass/' + ids,
    method: 'delete'
  })
}

// 导出教学资源分类
export function exptResourceclass(data) {
  return request({
    url: '/labsys/resourceclass/export',
    method: 'post',
    data: data
  })
}
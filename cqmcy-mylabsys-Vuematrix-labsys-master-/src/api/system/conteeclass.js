import request from '@/utils/request'

// 查询内容类型列表
export function listConteeclass (data) {
  return request({
    url: '/system/conteeclass/list',
    method: 'post',
    data: data
  })
}

// 查询内容类型树结构
export function treeConteeclass () {
  return request({
    url: '/system/conteeclass/tree',
    method: 'get'
  })
}

export function getBasicInfo(id) {
  return request({
    url: '/system/conteeclass/info/' + id,
    method: 'get'
  })
}

// 查询内容类型详细
export function getConteeclass (id) {
  return request({
    url: '/system/conteeclass/' + id,
    method: 'get'
  })
}

// 新增内容类型
export function addConteeclass (data) {
  return request({
    url: '/system/conteeclass',
    method: 'post',
    data: data
  })
}

// 修改内容类型
export function uptConteeclass (data) {
  return request({
    url: '/system/conteeclass',
    method: 'put',
    data: data
  })
}

// 删除内容类型
export function delConteeclass (ids) {
  return request({
    url: '/system/conteeclass/' + ids,
    method: 'delete'
  })
}

// 导出内容类型
export function exptConteeclass (data) {
  return request({
    url: '/system/conteeclass/export',
    method: 'post',
    data: data
  })
}

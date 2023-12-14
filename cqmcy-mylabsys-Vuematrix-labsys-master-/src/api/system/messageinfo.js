import request from '@/utils/request'

// 查询内容信息列表
export function listMessageinfo (data) {
  return request({
    url: '/system/messageinfo/list',
    method: 'post',
    data: data
  })
}

export function getBasicInfo(id) {
  return request({
    url: '/system/messageinfo/info/' + id,
    method: 'get'
  })
}

// 查询内容信息详细
export function getMessageinfo (id) {
  return request({
    url: '/system/messageinfo/' + id,
    method: 'get'
  })
}

// 新增内容信息
export function addMessageinfo (data) {
  return request({
    url: '/system/messageinfo/insert',
    method: 'post',
    data: data
  })
}

// 修改内容信息
export function uptMessageinfo (data) {
  return request({
    url: '/system/messageinfo/update',
    method: 'post',
    data: data
  })
}

// 删除内容信息
export function delMessageinfo (ids) {
  return request({
    url: '/system/messageinfo/' + ids,
    method: 'delete'
  })
}

// 导出内容信息
export function exptMessageinfo (data) {
  return request({
    url: '/system/messageinfo/export',
    method: 'post',
    data: data
  })
}

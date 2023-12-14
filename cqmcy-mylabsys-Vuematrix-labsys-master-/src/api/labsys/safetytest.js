import request from '@/utils/request'

// 查询安全考核列表
export function listSafetytest(data) {
  return request({
    url: '/labsys/safetytest/list',
    method: 'post',
    data: data
  })
}

// 查询安全考核详细
export function getSafetytest(id) {
  return request({
    url: '/labsys/safetytest/' + id,
    method: 'get'
  })
}

// 新增安全考核
export function addSafetytest(data) {
  return request({
    url: '/labsys/safetytest',
    method: 'post',
    data: data
  })
}

// 修改安全考核
export function uptSafetytest(data) {
  return request({
    url: '/labsys/safetytest',
    method: 'put',
    data: data
  })
}

// 删除安全考核
export function delSafetytest(ids) {
  return request({
    url: '/labsys/safetytest/' + ids,
    method: 'delete'
  })
}

// 导出安全考核
export function exptSafetytest(data) {
  return request({
    url: '/labsys/safetytest/export',
    method: 'post',
    data: data
  })
}
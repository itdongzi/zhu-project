import request from '@/utils/request'

// 查询安全学习资料列表
export function listSafetyfile(data) {
  return request({
    url: '/labsys/safetyfile/list',
    method: 'post',
    data: data
  })
}

// 查询安全学习资料详细
export function getSafetyfile(id) {
  return request({
    url: '/labsys/safetyfile/' + id,
    method: 'get'
  })
}

// 新增安全学习资料
export function addSafetyfile(data) {
  return request({
    url: '/labsys/safetyfile',
    method: 'post',
    data: data
  })
}

// 修改安全学习资料
export function uptSafetyfile(data) {
  return request({
    url: '/labsys/safetyfile',
    method: 'put',
    data: data
  })
}

// 删除安全学习资料
export function delSafetyfile(ids) {
  return request({
    url: '/labsys/safetyfile/' + ids,
    method: 'delete'
  })
}

// 导出安全学习资料
export function exptSafetyfile(data) {
  return request({
    url: '/labsys/safetyfile/export',
    method: 'post',
    data: data
  })
}
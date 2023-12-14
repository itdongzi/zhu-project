import request from '@/utils/request'

// 查询安全资料分类列表
export function listSafetyclass(data) {
  return request({
    url: '/labsys/safetyclass/list',
    method: 'post',
    data: data
  })
}

// 查询安全资料分类详细
export function getSafetyclass(id) {
  return request({
    url: '/labsys/safetyclass/' + id,
    method: 'get'
  })
}

// 新增安全资料分类
export function addSafetyclass(data) {
  return request({
    url: '/labsys/safetyclass',
    method: 'post',
    data: data
  })
}

// 修改安全资料分类
export function uptSafetyclass(data) {
  return request({
    url: '/labsys/safetyclass',
    method: 'put',
    data: data
  })
}

// 删除安全资料分类
export function delSafetyclass(ids) {
  return request({
    url: '/labsys/safetyclass/' + ids,
    method: 'delete'
  })
}

// 导出安全资料分类
export function exptSafetyclass(data) {
  return request({
    url: '/labsys/safetyclass/export',
    method: 'post',
    data: data
  })
}
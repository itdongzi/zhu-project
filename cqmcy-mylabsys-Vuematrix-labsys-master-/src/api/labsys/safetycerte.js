import request from '@/utils/request'

// 查询安全证书列表
export function listSafetycerte(data) {
  return request({
    url: '/labsys/safetycerte/list',
    method: 'post',
    data: data
  })
}

// 查询安全证书详细
export function getSafetycerte(id) {
  return request({
    url: '/labsys/safetycerte/' + id,
    method: 'get'
  })
}

// 新增安全证书
export function addSafetycerte(data) {
  return request({
    url: '/labsys/safetycerte',
    method: 'post',
    data: data
  })
}

// 修改安全证书
export function uptSafetycerte(data) {
  return request({
    url: '/labsys/safetycerte',
    method: 'put',
    data: data
  })
}

// 删除安全证书
export function delSafetycerte(ids) {
  return request({
    url: '/labsys/safetycerte/' + ids,
    method: 'delete'
  })
}

// 导出安全证书
export function exptSafetycerte(data) {
  return request({
    url: '/labsys/safetycerte/export',
    method: 'post',
    data: data
  })
}
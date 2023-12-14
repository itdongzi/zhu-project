import request from '@/utils/request'

// 查询竞赛报名信息列表
export function listContestenroll(data) {
  return request({
    url: '/labsys/contestenroll/list',
    method: 'post',
    data: data
  })
}

// 查询竞赛报名信息详细
export function getContestenroll(id) {
  return request({
    url: '/labsys/contestenroll/' + id,
    method: 'get'
  })
}

// 新增竞赛报名信息
export function addContestenroll(data) {
  return request({
    url: '/labsys/contestenroll',
    method: 'post',
    data: data
  })
}

// 修改竞赛报名信息
export function uptContestenroll(data) {
  return request({
    url: '/labsys/contestenroll',
    method: 'put',
    data: data
  })
}

// 删除竞赛报名信息
export function delContestenroll(ids) {
  return request({
    url: '/labsys/contestenroll/' + ids,
    method: 'delete'
  })
}

// 导出竞赛报名信息
export function exptContestenroll(data) {
  return request({
    url: '/labsys/contestenroll/export',
    method: 'post',
    data: data
  })
}
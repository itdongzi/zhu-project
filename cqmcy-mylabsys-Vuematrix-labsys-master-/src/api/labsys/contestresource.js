import request from '@/utils/request'

// 查询竞赛资源信息列表
export function listContestresource(data) {
  return request({
    url: '/labsys/contestresource/list',
    method: 'post',
    data: data
  })
}

// 查询竞赛资源信息详细
export function getContestresource(id) {
  return request({
    url: '/labsys/contestresource/' + id,
    method: 'get'
  })
}

// 新增竞赛资源信息
export function addContestresource(data) {
  return request({
    url: '/labsys/contestresource',
    method: 'post',
    data: data
  })
}

// 修改竞赛资源信息
export function uptContestresource(data) {
  return request({
    url: '/labsys/contestresource',
    method: 'put',
    data: data
  })
}

// 删除竞赛资源信息
export function delContestresource(ids) {
  return request({
    url: '/labsys/contestresource/' + ids,
    method: 'delete'
  })
}

// 导出竞赛资源信息
export function exptContestresource(data) {
  return request({
    url: '/labsys/contestresource/export',
    method: 'post',
    data: data
  })
}
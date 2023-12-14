import request from '@/utils/request'

// 查询竞赛小组信息列表
export function listContestgroup(data) {
  return request({
    url: '/labsys/contestgroup/list',
    method: 'post',
    data: data
  })
}

// 查询竞赛小组信息详细
export function getContestgroup(id) {
  return request({
    url: '/labsys/contestgroup/' + id,
    method: 'get'
  })
}

// 新增竞赛小组信息
export function addContestgroup(data) {
  return request({
    url: '/labsys/contestgroup',
    method: 'post',
    data: data
  })
}

// 修改竞赛小组信息
export function uptContestgroup(data) {
  return request({
    url: '/labsys/contestgroup',
    method: 'put',
    data: data
  })
}

// 删除竞赛小组信息
export function delContestgroup(ids) {
  return request({
    url: '/labsys/contestgroup/' + ids,
    method: 'delete'
  })
}

// 导出竞赛小组信息
export function exptContestgroup(data) {
  return request({
    url: '/labsys/contestgroup/export',
    method: 'post',
    data: data
  })
}
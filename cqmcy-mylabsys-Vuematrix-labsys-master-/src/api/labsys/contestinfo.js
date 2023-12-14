import request from '@/utils/request'

// 查询竞赛活动信息列表
export function listContestinfo(data) {
  return request({
    url: '/labsys/contestinfo/list',
    method: 'post',
    data: data
  })
}

// 查询竞赛活动信息详细
export function getContestinfo(id) {
  return request({
    url: '/labsys/contestinfo/' + id,
    method: 'get'
  })
}

// 新增竞赛活动信息
export function addContestinfo(data) {
  return request({
    url: '/labsys/contestinfo',
    method: 'post',
    data: data
  })
}

// 修改竞赛活动信息
export function uptContestinfo(data) {
  return request({
    url: '/labsys/contestinfo',
    method: 'put',
    data: data
  })
}

// 删除竞赛活动信息
export function delContestinfo(ids) {
  return request({
    url: '/labsys/contestinfo/' + ids,
    method: 'delete'
  })
}

// 导出竞赛活动信息
export function exptContestinfo(data) {
  return request({
    url: '/labsys/contestinfo/export',
    method: 'post',
    data: data
  })
}
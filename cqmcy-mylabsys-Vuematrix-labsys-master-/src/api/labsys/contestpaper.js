import request from '@/utils/request'

// 查询竞赛试题信息列表
export function listContestpaper(data) {
  return request({
    url: '/labsys/contestpaper/list',
    method: 'post',
    data: data
  })
}

// 查询竞赛试题信息详细
export function getContestpaper(id) {
  return request({
    url: '/labsys/contestpaper/' + id,
    method: 'get'
  })
}

// 新增竞赛试题信息
export function addContestpaper(data) {
  return request({
    url: '/labsys/contestpaper',
    method: 'post',
    data: data
  })
}

// 修改竞赛试题信息
export function uptContestpaper(data) {
  return request({
    url: '/labsys/contestpaper',
    method: 'put',
    data: data
  })
}

// 删除竞赛试题信息
export function delContestpaper(ids) {
  return request({
    url: '/labsys/contestpaper/' + ids,
    method: 'delete'
  })
}

// 导出竞赛试题信息
export function exptContestpaper(data) {
  return request({
    url: '/labsys/contestpaper/export',
    method: 'post',
    data: data
  })
}
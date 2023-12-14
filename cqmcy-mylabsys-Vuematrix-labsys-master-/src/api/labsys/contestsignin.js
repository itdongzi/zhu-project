import request from '@/utils/request'

// 查询竞赛打卡信息列表
export function listContestsignin(data) {
  return request({
    url: '/labsys/contestsignin/list',
    method: 'post',
    data: data
  })
}

// 查询竞赛打卡信息详细
export function getContestsignin(id) {
  return request({
    url: '/labsys/contestsignin/' + id,
    method: 'get'
  })
}

// 新增竞赛打卡信息
export function addContestsignin(data) {
  return request({
    url: '/labsys/contestsignin',
    method: 'post',
    data: data
  })
}

// 修改竞赛打卡信息
export function uptContestsignin(data) {
  return request({
    url: '/labsys/contestsignin',
    method: 'put',
    data: data
  })
}

// 删除竞赛打卡信息
export function delContestsignin(ids) {
  return request({
    url: '/labsys/contestsignin/' + ids,
    method: 'delete'
  })
}

// 导出竞赛打卡信息
export function exptContestsignin(data) {
  return request({
    url: '/labsys/contestsignin/export',
    method: 'post',
    data: data
  })
}
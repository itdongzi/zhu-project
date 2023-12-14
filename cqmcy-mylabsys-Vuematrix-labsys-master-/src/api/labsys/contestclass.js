import request from '@/utils/request'

// 查询竞赛类型列表
export function listContestclass(data) {
  return request({
    url: '/labsys/contestclass/list',
    method: 'post',
    data: data
  })
}
 
export function treeContestclass() {
  return request({
    url: '/labsys/contestclass/tree',
    method: 'get'
  })
}

// 查询竞赛类型详细
export function getContestclass(id) {
  return request({
    url: '/labsys/contestclass/' + id,
    method: 'get'
  })
}

// 新增竞赛类型
export function addContestclass(data) {
  return request({
    url: '/labsys/contestclass',
    method: 'post',
    data: data
  })
}

// 修改竞赛类型
export function uptContestclass(data) {
  return request({
    url: '/labsys/contestclass',
    method: 'put',
    data: data
  })
}

// 删除竞赛类型
export function delContestclass(ids) {
  return request({
    url: '/labsys/contestclass/' + ids,
    method: 'delete'
  })
}

// 导出竞赛类型
export function exptContestclass(data) {
  return request({
    url: '/labsys/contestclass/export',
    method: 'post',
    data: data
  })
}
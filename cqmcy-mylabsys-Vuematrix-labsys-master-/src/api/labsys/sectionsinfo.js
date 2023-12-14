import request from '@/utils/request'

// 查询教学节次时间列表
export function listSectionsinfo(data) {
  return request({
    url: '/labsys/sectionsinfo/list',
    method: 'post',
    data: data
  })
}

// 查询教学节次时间详细
export function getSectionsinfo(id) {
  return request({
    url: '/labsys/sectionsinfo/' + id,
    method: 'get'
  })
}

// 新增教学节次时间
export function addSectionsinfo(data) {
  return request({
    url: '/labsys/sectionsinfo',
    method: 'post',
    data: data
  })
}

// 修改教学节次时间
export function uptSectionsinfo(data) {
  return request({
    url: '/labsys/sectionsinfo',
    method: 'put',
    data: data
  })
}

// 删除教学节次时间
export function delSectionsinfo(ids) {
  return request({
    url: '/labsys/sectionsinfo/' + ids,
    method: 'delete'
  })
}

// 导出教学节次时间
export function exptSectionsinfo(data) {
  return request({
    url: '/labsys/sectionsinfo/export',
    method: 'post',
    data: data
  })
}
import request from '@/utils/request'

// 查询学期学年信息列表
export function listSemesterinfo(data) {
  return request({
    url: '/labsys/semesterinfo/list',
    method: 'post',
    data: data
  })
}

// 查询学期学年信息详细
export function getSemesterinfo(id) {
  return request({
    url: '/labsys/semesterinfo/' + id,
    method: 'get'
  })
}

// 新增学期学年信息
export function addSemesterinfo(data) {
  return request({
    url: '/labsys/semesterinfo',
    method: 'post',
    data: data
  })
}

// 修改学期学年信息
export function uptSemesterinfo(data) {
  return request({
    url: '/labsys/semesterinfo',
    method: 'put',
    data: data
  })
}

// 删除学期学年信息
export function delSemesterinfo(ids) {
  return request({
    url: '/labsys/semesterinfo/' + ids,
    method: 'delete'
  })
}

// 导出学期学年信息
export function exptSemesterinfo(data) {
  return request({
    url: '/labsys/semesterinfo/export',
    method: 'post',
    data: data
  })
}
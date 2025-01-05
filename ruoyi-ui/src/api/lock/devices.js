import request from '@/utils/request'

// 查询设备列表
export function listDevices(query) {
  return request({
    url: '/lock/devices/list',
    method: 'get',
    params: query
  })
}

// 查询设备详细
export function getDevices(deviceId) {
  return request({
    url: '/lock/devices/' + deviceId,
    method: 'get'
  })
}

// 新增设备
export function addDevices(data) {
  return request({
    url: '/lock/devices',
    method: 'post',
    data: data
  })
}

// 修改设备
export function updateDevices(data) {
  return request({
    url: '/lock/devices',
    method: 'put',
    data: data
  })
}

// 删除设备
export function delDevices(deviceId) {
  return request({
    url: '/lock/devices/' + deviceId,
    method: 'delete'
  })
}

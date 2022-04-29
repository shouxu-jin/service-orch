let menuListA = {
    defaultOpeneds: ['1'],
    menuList: [
        {
            id: '1',
            type: 'group',
            name: '节点列表',
            ico: 'el-icon-video-play',
            open: true,
            children: [
                {
                    id: '11',
                    type: 'start',
                    name: '开始节点',
                    ico: 'el-icon-time',
                    // 自定义覆盖样式
                    style: {}
                }, {
                id: '12',
                type: 'end',
                name: '结束节点',
                ico: 'el-icon-time',
                // 自定义覆盖样式
                style: {}
              }, {
                id: '13',
                type: 'param',
                name: '参数提取节点',
                ico: 'el-icon-odometer',
                // 自定义覆盖样式
                style: {}
              }, {
                id: '14',
                type: 'http',
                name: 'http请求节点',
                ico: 'el-icon-odometer',
                // 自定义覆盖样式
                style: {}
              }, {
                id: '15',
                type: 'result',
                name: '结果响应节点',
                ico: 'el-icon-odometer',
                // 自定义覆盖样式
                style: {}
              }
            ]
        }
    ]
}

export function menuList () {
    return menuListA
}

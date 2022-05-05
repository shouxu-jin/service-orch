<template>
  <div>
    <div class="ef-node-form">
      <div class="ef-node-form-header">
        编辑
      </div>
      <div class="ef-node-form-body">
        <el-form :model="node" ref="dataForm" label-width="80px" v-show="type === 'node'">
          <el-form-item label="类型">
            <el-input v-model="node.type" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="名称">
            <el-input v-model="node.name"></el-input>
          </el-form-item>
          <el-form-item label="left坐标">
            <el-input v-model="node.left" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="top坐标">
            <el-input v-model="node.top" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="ico图标">
            <el-input v-model="node.ico" :disabled="true"></el-input>
          </el-form-item>

          <el-form-item label="参数名" v-show="node.type === 'store'">
            <el-input v-model="node.storeName"></el-input>
          </el-form-item>
          <el-form-item label="参数表达式" v-show="node.type === 'store'">
            <el-input v-model="node.expression"></el-input>
          </el-form-item>

          <el-form-item label="返回模板" v-show="node.type === 'result'">
            <el-input v-model="node.template"></el-input>
          </el-form-item>

          <el-form-item label="请求地址" v-show="node.type === 'http'">
            <el-input v-model="node.url"></el-input>
          </el-form-item>
          <el-form-item label="请求体" v-show="node.type === 'http'">
            <el-input v-model="node.body"></el-input>
          </el-form-item>
          <el-form-item label="请求头" v-show="node.type === 'http'">
            <el-input v-model="node.heanders"></el-input>
          </el-form-item>
          <el-form-item label="响应结果名称" v-show="node.type === 'http'">
            <el-input v-model="node.resultName"></el-input>
          </el-form-item>

          <el-form-item label="转换输入" v-show="node.type === 'jsonTransform'">
            <el-input v-model="node.transformFrom"></el-input>
          </el-form-item>
          <el-form-item label="转换输出" v-show="node.type === 'jsonTransform'">
            <el-input v-model="node.transformTo"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" icon="el-icon-check" @click="save">保存</el-button>
          </el-form-item>
        </el-form>

        <el-form :model="line" ref="dataForm" label-width="80px" v-show="type === 'line'">
          <el-form-item label="类型">
            <el-select v-model="line.type">
              <el-option label="直连" value="direct"></el-option>
              <el-option label="条件" value="condition"></el-option>
              <el-option label="循环" value="loop"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="条件">
            <el-input v-model="line.condition"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-check" @click="saveLine">保存</el-button>
          </el-form-item>
        </el-form>

        <el-form :model="board" ref="dataForm" label-width="80px" v-show="type === 'board'">
          <el-form-item label="接口名">
            <el-input v-model="board.name"></el-input>
          </el-form-item>
          <el-form-item label="serviceId">
            <el-input v-model="board.serviceId"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-check" @click="saveBoard">保存</el-button>
          </el-form-item>
        </el-form>
      </div>
      <!--            <div class="el-node-form-tag"></div>-->
    </div>
  </div>

</template>

<script>
    import {cloneDeep} from 'lodash'

    export default {
        data () {
            return {
                visible: true,
                // node 或 line
                type: '',
                node: {},
                line: {},
                board: {},
                data: {}
            }
        },
        methods: {
            /**
             * 表单修改，这里可以根据传入的ID进行业务信息获取
             * @param data
             * @param id
             */
            nodeInit (data, id) {
                this.type = 'node'
                this.data = data
                data.nodeList.filter((node) => {
                    if (node.id === id) {
                        this.node = cloneDeep(node)
                    }
                })
            },
            lineInit (line) {
                this.type = 'line'
                this.line = line
            },
            boardInit (board) {
                this.type = 'board'
                this.board = board
            },
            // 修改连线
            saveLine () {
                this.$emit('setLineCondition', this.line.from, this.line.to, this.line.type, this.line.condition)
            },
            // 修改流程名称
            saveBoard () {
                this.$emit('setBoardName', this.board)
            },
            save () {
                let newNode
                let index
                this.data.nodeList.filter((node, idx) => {
                    if (node.id === this.node.id) {
                        newNode = cloneDeep(this.node)
                        index = idx
                    }
                })
                this.data.nodeList.splice(index, 1)
                this.data.nodeList.push(newNode)
                this.$emit('repaintEverything')
            }
        }
    }
</script>

<style>
  .el-node-form-tag {
    position: absolute;
    top: 50%;
    margin-left: -15px;
    height: 40px;
    width: 15px;
    background-color: #fbfbfb;
    border: 1px solid rgb(220, 227, 232);
    border-right: none;
    z-index: 0;
  }
</style>

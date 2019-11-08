<template>
    <section>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-pie-chart"></i> 订单查询
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-card style="margin-top: 0;">
            <el-form
                :model="orderForm"
                label-position="left"
                :inline="true"
                label-width="70px"
                class="formStyle"
            >
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="金额下限">
                            <el-input
                                v-model="orderForm.amountDown"
                                :maxlength="12"
                                placeholder="金额下限"
                                clearable
                            ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="金额上限">
                            <el-input
                                v-model="orderForm.amountUp"
                                :maxlength="6"
                                placeholder="金额上限"
                                clearable
                            ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="用户ID">
                            <el-input
                                v-model="orderForm.userId "
                                :maxlength="12"
                                placeholder="用户ID"
                                clearable
                            ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="8">
                        <el-form-item label="起始日期">
                            <el-date-picker
                                type="date"
                                placeholder="起始日期"
                                value-format="yyyy-MM-dd"
                                v-model="orderForm.createTimeStart "
                            ></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="结束日期">
                            <el-date-picker
                                type="date"
                                placeholder="结束日期"
                                value-format="yyyy-MM-dd"
                                v-model="orderForm.createTimeEnd"
                            ></el-date-picker>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="订单号">
                            <el-input
                                v-model="orderForm.id "
                                :maxlength="12"
                                placeholder="订单号"
                                clearable
                            ></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row class="rel">
                    <el-col :span="8">
                        <el-form-item label="用户名">
                            <el-input
                                v-model="orderForm.nickName "
                                :maxlength="12"
                                placeholder="用户名"
                                clearable
                            ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="商品名称">
                            <el-input
                                v-model="orderForm.productName "
                                :maxlength="12"
                                placeholder="商品名称"
                                clearable
                            ></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8" class="btnItem">
                        <el-button size="medium" type="primary" @click="selectProcess">查询</el-button>
                        <el-button size="medium" type="primary" @click="reset" class="resetBtn">重置</el-button>
                    </el-col>
                </el-row>
            </el-form>
        </el-card>
        <el-card>
            <el-table :data="content">
                <el-table-column label="订单号" prop="order.id"></el-table-column>
                <el-table-column label="用户ID" prop="order.userId"></el-table-column>
                <el-table-column label="金额" prop="order.amount"></el-table-column>
                <el-table-column label="创建时间" prop="order.createTime"></el-table-column>
                <el-table-column label="支付时间" prop="order.payTime"></el-table-column>
                <el-table-column label="最后修改时间" prop="order.lastTime"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button @click="handleClick(scope.row,true)" type="text" size="small">同意</el-button>
                        <el-button @click="handleClick(scope.row,false)" type="text" size="small">拒绝</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                layout="total, prev, pager, next"
                @current-change="handleCurrentChange"
                :page-size="10"
                :total="total"
                style="float:right;"
            ></el-pagination>
        </el-card>
        <el-dialog title="拒绝" :visible.sync="dialogFormVisible">
            <el-form :model="submitlist">
                <el-form-item label="理由">
                    <el-input v-model="submitlist.rejectReason"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="submit">确 定</el-button>
            </div>
        </el-dialog>
    </section>
</template>
<script>
import axios from 'axios';
export default {
    data() {
        return {
            orderForm: {
                amountDown: '',
                amountUp: '',
                status: '申请退款',
                createTimeStart: '',
                createTimeEnd: '',
                id: '',
                nickName: '',
                productName: '',
                userId: ''
            },
            List: [],
            total: 0,
            page: 1,
            size: 10,
            content: [],
            submitlist: {
                orderId: "",
                rejectReason: "",
                userId: "",
                verifyStatus: ""
            },
            dialogFormVisible:false
        };
    },
    created() {},
    methods: {
        //重置
        reset() {
            this.orderForm = {
                amountDown: '',
                amountUp: '',
                status: '',
                createTimeStart: '',
                createTimeEnd: '',
                id: '',
                nickName: '',
                productName: '',
                userId: ''
            };
        },
        //分页相关

        handleCurrentChange(val) {
            this.page = val;
            this.selectProcess();
        },
        getList() {
            axios
                .get(`http://49.233.85.147:9999/zzshx/order/stateReady`, {
                    headers: { token: sessionStorage.getItem('token') }
                })
                .then(res => {
                    if (res.data.code == 200) {
                        this.List = res.data.resultBody;
                    } else {
                        this.$message.error(res.data.resultBody);
                    }
                });
        },
        selectProcess() {
            for (let key in this.orderForm) {
                if (this.orderForm[key] == null) {
                    this.orderForm[key] = '';
                }
            }
            if (this.orderForm.createTimeStart != '') {
                this.orderForm.createTimeStart += 'T00:00:00.0000';
            }
            if (this.orderForm.createTimeEnd != '') {
                this.orderForm.createTimeEnd += 'T00:00:00.0000';
            }
            if (this.orderForm.amountDown != '') {
                this.orderForm.amountDown *= 1;
            }
            if (this.orderForm.amountUp != '') {
                this.orderForm.amountUp *= 1;
            }
            axios
                .post(`http://49.233.85.147:9999/zzshx/order/${this.page}/${this.size}`, this.orderForm, {
                    headers: { token: sessionStorage.getItem('token') }
                })
                .then(res => {
                    if (res.data.code == 200) {
                        this.total = res.data.resultBody.totalElements;
                        this.content = res.data.resultBody.content;
                    } else {
                        this.$message.error(res.data.resultBody);
                    }
                });
        },
        reset() {},
        handleClick(row, type) {
            this.submitlist.orderId = row.order.id,
            this.submitlist.userId = row.order.userId,
            this.submitlist.verifyStatus = type
            if (type == true) {
                this.submit()
            } else {
                this.dialogFormVisible = true
            }
        },
        submit() {
            axios
                .post(`http://49.233.85.147:9999/zzshx/order/refund/verify`, this.submitlist, {
                    headers: { token: sessionStorage.getItem('token') }
                })
                .then(res => {
                    if (res.data.code == 200) {
                        this.$message.succss("成功");
                    } else {
                        this.$message.error(res.data.resultBody);
                    }
                });
        }
    },
    mounted() {
        this.getList();
    }
};
</script>

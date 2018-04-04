/**
 * Created by Caby on 6/3/17.
 */
;(function () {
    function agentListFunc() {
        var _utils = layui.commonUtils, _loading = layui.commonLoading, $ = layui.jquery, _layer = layui.layer,
            _dateFormat = layui.dateFormat, _pager = layui.laypage, _form = layui.form, _md5 = layui.md5,
            _sha1 = layui.sha1, _multipleCheckbox = layui.multipleCheckbox;
        // fetch data
        var _agentListContainer = $('#agentListContainer');
        var _agentList = {};
        function getAgentList(obj, first) {
            if (first) return;
            var pageNo = obj.curr;
            _agentListContainer.html('');
            _loading.showLoading();
            $.get('/api/agent/list', {pageNo: pageNo - 1}, function (result) {
                _loading.hideLoading();
                $('.layui-table').show();
                var pageSize = 20, totalCount = 0;
                if (result.code == 0) {
                    pageNo = result.content.pageNo + 1;
                    pageSize = result.content.pageSize;
                    totalCount = result.content.agentCount;
                    var agentList = result.content.agentList;
                    $.each(agentList, function (idx, agent) {
                        var tr = $('<tr class="user_info_row" data-nid="' + agent.id + '"></tr>');
                        tr.append('<td>' + (idx + 1) + '</td>');
                        tr.append('<td>' + agent.name + '</td>');
                        tr.append('<td>' + agent.telephone + '</td>');
                        tr.append('<td>' + agent.id_num + '</td>');
                        tr.append('<td>' + (agent.sex == 0 ? '女' : '男') + '</td>');
                        tr.append('<td><div class="layui-btn-group" data-uid="' + agent.id_num + '">'
                            +   '<button class="layui-btn layui-btn-primary layui-btn-small btn_set_agent"' +
                            ' title="编辑">'
                            +       '<i class="layui-icon">&#xe642;</i>'
                            +   '</button>'
                            +   '<button class="layui-btn layui-btn-primary layui-btn-small btn_find_customer"' +
                            ' title="查看客户">'
                            +       '<i class="layui-icon">&#xe613;</i>'
                            +   '</button>'
                            +   '<button class="layui-btn layui-btn-primary layui-btn-small btn_find_creditcard"' +
                            ' title="查看银行卡">'
                            +       '<i class="layui-icon">&#xe65e;</i>'
                            +   '</button>'
                            + '</div></td>');
                        _agentListContainer.append(tr);
                        _agentList[agent.id] = agent;
                    });
                } else {
                    _layer.msg(result.msg ? result.msg : '获取用户列表失败');
                }
                if (!first) {
                    setTimeout(function () {
                        _pager.render({elem: 'pagination', count: totalCount, limit: pageSize, curr: pageNo, jump: getAgentList});
                    }, 0);
                }
            });
        }
        // find customer
        var _bodyEle = $('body');
        _bodyEle.on('click', '.btn_find_customer', function () {
            var uid = $(this).parent().attr('data-uid');
            if (!uid) {
                layer.msg('找不到经纪人的id，无法进行查找，请刷新页面再试');
                return;
            }
            showFindCustomerDialog(uid);
        });
        // find customer
        var _btnFindCustomer = $('#btn_find_customer');
        var _findCustomerHtmlContainer = $('#find_customer_html_container');
        var _dialogIdx = false;
        function showFindCustomerDialog(uid) {
            _dialogIdx = _layer.open({
                type: 1,
                area: ['800px', '520px'],
                title: '顾客信息',
                content: _findCustomerHtmlContainer.html(),
                cancel: closeFindCustomerDialog
            });

            getCustomerList({curr: 1, agentIdNum: uid});
        }
        // close find customer
        function closeFindCustomerDialog() {
            if (_dialogIdx !== false) {
                _layer.close(_dialogIdx);
            }
            _btnFindCustomer.removeClass('layui-btn-disabled');
            _dialogIdx = false;
        }
        var _customerListContainer = $('#findCustomerContainer');
        var _customerList = {};
        function getCustomerList(obj, first) {
            if (first) return;
            var pageNo = obj.curr;
            _customerListContainer.html('');
            _loading.showLoading();
            $.get('/api/customer/list', {pageNo: pageNo - 1, agentIdNum: obj.agentIdNum}, function (result) {
                _loading.hideLoading();
                $('.layui-table').show();
                var pageSize = 20, totalCount = 0;
                if (result.code == 0) {
                    pageNo = result.content.pageNo + 1;
                    pageSize = result.content.pageSize;
                    totalCount = result.content.customerCount;
                    var agentList = result.content.customerList;
                    $.each(agentList, function (idx, customer) {
                        var tr = $('<tr class="user_info_row" data-nid="' + customer.id + '"></tr>');
                        tr.append('<td>' + (idx + 1) + '</td>');
                        tr.append('<td>' + customer.name + '</td>');
                        tr.append('<td>' + customer.telephone + '</td>');
                        tr.append('<td>' + (customer.sex == 0 ? '女' : '男') + '</td>');
                        tr.append('<td><div class="layui-btn-group" data-uid="' + agent.id + '">'
                            +   '<button class="layui-btn layui-btn-primary layui-btn-small btn_set_agent"' +
                            ' title="编辑">'
                            +       '<i class="layui-icon">&#xe642;</i>'
                            +   '</button>'
                            + '</div></td>');
                        _customerListContainer.append(tr);
                        _customerList[agent.id] = agent;
                    });
                } else {
                    _layer.msg(result.msg ? result.msg : '获取顾客列表失败');
                }
                if (!first) {
                    setTimeout(function () {
                        _pager.render({elem: 'pagination', count: totalCount, limit: pageSize, curr: pageNo, jump: getCustomerList});
                    }, 0);
                }
            });
        }

        // get first page
        getAgentList({curr: 1});
    }



    layui.use(
        [
            'laypage',
            'pageBuilder',
            'commonUtils',
            'commonLoading',
            'jquery',
            'layer',
            'dateFormat',
            'form',
            'md5',
            'sha1',
            'multipleCheckbox'
        ],
        agentListFunc
    );
} ());
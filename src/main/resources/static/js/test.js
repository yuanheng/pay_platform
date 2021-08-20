(window.webpackJsonp = window.webpackJsonp || []).push([[7], {
    354: function(e, t, a) {
        "use strict";
        var n = a(56)
            , r = a.n(n)
            , s = a(57)
            , i = a.n(s)
            , c = a(58)
            , u = a.n(c)
            , o = a(59)
            , l = a.n(o)
            , m = a(60)
            , d = a.n(m)
            , p = a(10)
            , h = a.n(p)
            , f = a(351)
            , v = a.n(f)
            , E = (a(355),
            function(e) {
                function t() {
                    return r()(this, t),
                        u()(this, l()(t).apply(this, arguments))
                }
                return d()(t, e),
                    i()(t, [{
                        key: "render",
                        value: function() {
                            return h.a.createElement("div", {
                                className: v.a.header
                            }, h.a.createElement("div", {
                                className: v.a.wrap
                            }, h.a.createElement("div", {
                                className: v.a.logo_box
                            })))
                        }
                    }]),
                    t
            }(p.Component));
        t.a = E
    },
    355: function(e, t, a) {
        e.exports = a.p + "images/7f7289eeb6a88c2ad4b2be9eecefa722.png"
    },
    356: function(e, t, a) {
        "use strict";
        t.a = function(e, t) {
            var a = new RegExp("(^|&)" + e + "=([^&]*)(&|$)")
                , n = t.substr(1).match(a);
            return n ? decodeURIComponent(n[2]) : null
        }
    },
    361: function(e, t, a) {
        "use strict";
        function n(e) {
            return e && !isNaN(e) || (e = 0),
                parseFloat(e).toFixed(8)
        }
        function r(e) {
            var t = 1 < arguments.length && void 0 !== arguments[1] && arguments[1];
            return e && !isNaN(e) || (e = 0),
                e = parseFloat(e).toFixed(2),
            t && (e = "".concat(e, " CNY")),
                e
        }
        a.d(t, "b", function() {
            return n
        }),
            a.d(t, "a", function() {
                return r
            }),
            a(10)
    },
    362: function(e, t, a) {
        "use strict";
        var n = a(363)
            , r = a.n(n)
            , s = a(360)
            , i = a.n(s)
            , c = a(364)
            , u = a.n(c)
            , o = (a(365),
            a(366))
            , l = a.n(o)
            , m = {
            200: "服务器成功返回请求的数据。",
            201: "新建或修改数据成功。",
            202: "一个请求已经进入后台排队（异步任务）。",
            204: "删除数据成功。",
            400: "发出的请求有错误，服务器没有进行新建或修改数据的操作。",
            401: "用户没有权限（令牌、用户名、密码错误）。",
            403: "用户得到授权，但是访问是被禁止的。",
            404: "发出的请求针对的是不存在的记录，服务器没有进行操作。",
            406: "请求的格式不可得。",
            410: "请求的资源被永久删除，且不会再得到的。",
            422: "当创建一个对象时，发生一个验证错误。",
            500: "服务器发生错误，请检查服务器。",
            502: "网关错误。",
            503: "服务不可用，服务器暂时过载或维护。",
            504: "网关超时。"
        }
            , d = function(e) {
            if (200 <= e.status && e.status < 300)
                return e;
            var t = m[e.status] || e.statusText
                , a = new Error(t);
            throw a.name = e.status,
                a.response = e,
                a
        };
        function p(e, t, a) {
            var n = i()({
                expirys: !0
            }, t)
                , r = e + (n.body ? JSON.stringify(n.body) : "")
                , s = l.a.sha256().update(r).digest("hex")
                , c = i()({}, {
                credentials: "include"
            }, n);
            "POST" !== c.method && "PUT" !== c.method && "DELETE" !== c.method || (c.body instanceof FormData ? c.headers = i()({
                Accept: "application/json"
            }, c.headers) : (c.headers = i()({
                Accept: "application/json",
                "Content-Type": "application/json; charset=utf-8"
            }, c.headers),
                c.body = JSON.stringify(c.body)));
            var u = n.expirys && 5;
            if (!1 !== n.expirys) {
                var o = sessionStorage.getItem(s)
                    , m = sessionStorage.getItem("".concat(s, ":timestamp"));
                if (null !== o && null !== m) {
                    if ((Date.now() - m) / 1e3 < u)
                        return new Response(new Blob([o])).json();
                    sessionStorage.removeItem(s),
                        sessionStorage.removeItem("".concat(s, ":timestamp"))
                }
            }
            return fetch(e, c, a).then(d).then(function(e) {
                return "DELETE" === c.method || 204 === e.status ? e.text() : e.json()
            }).catch(function(e) {
                e.name
            })
        }
        function h(e) {
            return function() {
                return u()(r.a.mark(function e(t) {
                    return r.a.wrap(function(e) {
                        for (; ; )
                            switch (e.prev = e.next) {
                                case 0:
                                    return e.abrupt("return", p("/api/open/auth/replace", {
                                        method: "POST",
                                        body: i()({}, t)
                                    }));
                                case 1:
                                case "end":
                                    return e.stop()
                            }
                    }, e, this)
                })).apply(this, arguments)
            }
                .apply(this, arguments)
        }
        function f(e) {
            return function() {
                return u()(r.a.mark(function e(t) {
                    return r.a.wrap(function(e) {
                        for (; ; )
                            switch (e.prev = e.next) {
                                case 0:
                                    return e.abrupt("return", p("/api/open/auth/checkIdNumber", {
                                        method: "POST",
                                        body: i()({}, t)
                                    }));
                                case 1:
                                case "end":
                                    return e.stop()
                            }
                    }, e, this)
                })).apply(this, arguments)
            }
                .apply(this, arguments)
        }
        function v(e) {
            return function() {
                return u()(r.a.mark(function e(t) {
                    return r.a.wrap(function(e) {
                        for (; ; )
                            switch (e.prev = e.next) {
                                case 0:
                                    return e.abrupt("return", p("/api/open/auth/submit", {
                                        method: "POST",
                                        body: i()({}, t)
                                    }));
                                case 1:
                                case "end":
                                    return e.stop()
                            }
                    }, e, this)
                })).apply(this, arguments)
            }
                .apply(this, arguments)
        }
        function E(e) {
            return function() {
                return u()(r.a.mark(function e(t) {
                    return r.a.wrap(function(e) {
                        for (; ; )
                            switch (e.prev = e.next) {
                                case 0:
                                    return e.abrupt("return", p("/api/open/auth/getDetails", {
                                        method: "POST",
                                        body: i()({}, t)
                                    }));
                                case 1:
                                case "end":
                                    return e.stop()
                            }
                    }, e, this)
                })).apply(this, arguments)
            }
                .apply(this, arguments)
        }
        function b(e) {
            return function() {
                return u()(r.a.mark(function e(t) {
                    return r.a.wrap(function(e) {
                        for (; ; )
                            switch (e.prev = e.next) {
                                case 0:
                                    return e.abrupt("return", p("/api/open/auth/confirm", {
                                        method: "POST",
                                        body: i()({}, t)
                                    }));
                                case 1:
                                case "end":
                                    return e.stop()
                            }
                    }, e, this)
                })).apply(this, arguments)
            }
                .apply(this, arguments)
        }
        function y(e) {
            return function() {
                return u()(r.a.mark(function e(t) {
                    return r.a.wrap(function(e) {
                        for (; ; )
                            switch (e.prev = e.next) {
                                case 0:
                                    return e.abrupt("return", p("/api/open/auth/matching", {
                                        method: "POST",
                                        body: i()({}, t)
                                    }));
                                case 1:
                                case "end":
                                    return e.stop()
                            }
                    }, e, this)
                })).apply(this, arguments)
            }
                .apply(this, arguments)
        }
        a.d(t, "b", function() {
            return h
        }),
            a.d(t, "a", function() {
                return f
            }),
            a.d(t, "f", function() {
                return v
            }),
            a.d(t, "c", function() {
                return E
            }),
            a.d(t, "d", function() {
                return b
            }),
            a.d(t, "e", function() {
                return y
            })
    },
    397: function(e, t, a) {
        "use strict";
        a.r(t);
        var n, r = a(56), s = a.n(r), i = a(57), c = a.n(i), u = a(58), o = a.n(u), l = a(59), m = a.n(l), d = a(60), p = a.n(d), h = a(10), f = a.n(h), v = a(354), E = {
            checkIdCard: function(e) {
                if (e = e.toUpperCase(),
                    !/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(e))
                    return !1;
                var t, a, n;
                if (15 == (t = e.length)) {
                    a = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
                    var r = e.match(a);
                    if ((n = new Date("19" + r[2] + "/" + r[3] + "/" + r[4])).getYear() == Number(r[2]) && n.getMonth() + 1 == Number(r[3]) && n.getDate() == Number(r[4])) {
                        var s = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2)
                            , i = new Array("1","0","X","9","8","7","6","5","4","3","2")
                            , c = 0;
                        for (e = e.substr(0, 6) + "19" + e.substr(6, e.length - 6),
                                 u = 0; u < 17; u++)
                            c += e.substr(u, 1) * s[u];
                        return e + i[c % 11]
                    }
                    return !1
                }
                if (18 != t)
                    return !1;
                if (a = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/),
                    r = e.match(a),
                (n = new Date(r[2] + "/" + r[3] + "/" + r[4])).getFullYear() == Number(r[2]) && n.getMonth() + 1 == Number(r[3]) && n.getDate() == Number(r[4])) {
                    var u;
                    for (s = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2),
                             i = new Array("1","0","X","9","8","7","6","5","4","3","2"),
                             c = 0,
                             u = 0; u < 17; u++)
                        c += e.substr(u, 1) * s[u];
                    return i[c % 11] == e.substr(17, 1) && e
                }
                return !1
            },
            hasEmoji: function(e) {
                return !!e.match(/\uD83C[\uDF00-\uDFFF]|\uD83D[\uDC00-\uDE4F]/g)
            },
            isPhoneNum: function(e) {
                return -1 != e.search(/^1[3|4|5|7|8][0-9]{9}$/)
            },
            checkEmail: function(e) {
                return !!/\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/.test(e)
            },
            hasDoubleBitStr: function(e) {
                return !!/[^\x00-\xff]/.test(e)
            },
            hasChineseStr: function(e) {
                return !!/[\u4e00-\u9fa5]/.test(e)
            },
            isChineseStr: function(e) {
                return !!/^[\u4e00-\u9fa5]{2,4}$/.test(e)
            },
            stateUp: function(e, t) {
                e = e.split(".");
                var a = this.state;
                e.map(function(n, r) {
                    r + 1 == e.length ? a[n] = t : a = a[n]
                }),
                    this.forceUpdate()
            },
            objExistence: function(e, t) {
                var a = !1;
                for (var n in e)
                    n == t && e[n] && (a = !0);
                return a
            },
            checkLoginPwd: function(e) {
                return !!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,20}$/.test(e)
            },
            checkPayPwd: function(e) {
                return !!/^\d{6}$/.test(e)
            }
        }, b = a(362), y = a(361), N = a(102), g = a(30), w = a(356), x = a(357), k = a.n(x), D = a(352), S = a.n(D), T = null, C = Object(g.withRouter)(n = function(e) {
            function t(e) {
                var a;
                return s()(this, t),
                    (a = o()(this, m()(t).call(this, e))).nameRef = function(e) {
                        a.nameDom = e
                    }
                    ,
                    a.state = {
                        visible: !1,
                        loading: !1,
                        flag: !0,
                        virtualId: "",
                        payDisable: !0,
                        nameError: !1,
                        idcardError: !1,
                        orderDetail: {},
                        name: "",
                        idcard: ""
                    },
                    a
            }
            return p()(t, e),
                c()(t, [{
                    key: "componentWillMount",
                    value: function() {
                        var e = this
                            , t = Object(w.a)("virtualId", this.props.location.search);
                        this.setState({
                            virtualId: t
                        }, function() {
                            e.fetchOrderDetail(t)
                        })
                    }
                }, {
                    key: "componentDidMount",
                    value: function() {
                        history.pushState(null, null, document.URL),
                            window.addEventListener("popstate", function() {
                                history.pushState(null, null, document.URL)
                            })
                    }
                }, {
                    key: "componentWillUnmount",
                    value: function() {
                        clearInterval(T)
                    }
                }, {
                    key: "fetchOrderDetail",
                    value: function(e) {
                        var t = this
                            , a = {
                            virtualId: e
                        };
                        Object(b.b)(a).then(function(e) {
                            200 === e.code ? t.setState({
                                orderDetail: e.data
                            }) : t.redirectToErrorPage(e.msg)
                        })
                    }
                }, {
                    key: "addOrder",
                    value: function(e, t, a) {
                        var n = this;
                        if (!this.state.payDisable) {
                            var r = {
                                id: e,
                                inputName: t,
                                advertId: a
                            };
                            Object(b.f)(r).then(function(t) {
                                200 === t.code ? n.props.history.push("/pay?id=".concat(e)) : n.redirectToErrorPage(t.msg)
                            })
                        }
                    }
                }, {
                    key: "checkIdCardNumber",
                    value: function() {
                        var e = this;
                        this.state.flag && this.setState({
                            loading: !0,
                            flag: !1
                        }, function() {
                            var t = {
                                id: e.state.orderDetail.id,
                                inputName: e.nameDom.value,
                                virtualId: e.state.virtualId
                            };
                            Object(b.a)(t).then(function(t) {
                                200 === t.code ? T = setInterval(function() {
                                    e.timerTask(e.state.orderDetail.id)
                                }, 1e3) : e.redirectToErrorPage(t.msg)
                            })
                        })
                    }
                }, {
                    key: "timerTask",
                    value: function(e) {
                        var t = this
                            , a = {
                            id: e
                        };
                        Object(b.e)(a).then(function(a) {
                            if (2 === a.code) {
                                var n = t.nameDom.value;
                                clearInterval(T),
                                    t.addOrder(e, n, a.data)
                            } else
                                1 === a.code && t.redirectToErrorPage(a.msg)
                        })
                    }
                }, {
                    key: "redirectToErrorPage",
                    value: function(e) {
                        this.props.history.push("/error?errmsg=".concat(e))
                    }
                }, {
                    key: "handleInputChange",
                    value: function() {
                        this.checkForm()
                    }
                }, {
                    key: "checkForm",
                    value: function() {
                        var e = this.nameDom.value;
                        E.isChineseStr(e) ? this.setState({
                            payDisable: !1,
                            nameError: !1
                        }) : this.setState({
                            payDisable: !0
                        })
                    }
                }, {
                    key: "checkName",
                    value: function(e) {
                        if (E.isChineseStr(e))
                            return !0
                    }
                }, {
                    key: "checkIdCard",
                    value: function(e) {
                        if (E.checkIdCard(e))
                            return !0
                    }
                }, {
                    key: "render",
                    value: function() {
                        var e = this
                            , t = this.state.orderDetail;
                        return f.a.createElement(f.a.Fragment, null, f.a.createElement(v.a, null), f.a.createElement("div", {
                            className: S.a.main
                        }, f.a.createElement("div", {
                            className: k()(S.a.wrap, S.a.content_box)
                        }, f.a.createElement("div", {
                            className: S.a.steps_box
                        }, f.a.createElement("div", {
                            className: S.a.steps
                        }, f.a.createElement("div", {
                            className: S.a.title
                        }, "第1步（共2步）：", f.a.createElement("span", null, "确认付款信息")), f.a.createElement("div", {
                            className: S.a.pay_info
                        }, f.a.createElement("div", {
                            className: S.a.pay_msg
                        }, f.a.createElement("span", {
                            id: "popover",
                            className: S.a.question
                        }, "什么是USDT？"), f.a.createElement("div", {
                            className: S.a.popover_box
                        }, f.a.createElement("div", {
                            className: S.a.popover_arrow
                        }), f.a.createElement("div", {
                            className: S.a.popover
                        }, "USDT是数字货币领域里的主流币种，与美元1比1锚定。在全球大部分数字货币交易所中作为标准的计价单位和价值尺度。USDT具有全球通用、约等于美元价格的特性，在越来越多的数字货币场景中成为美元 替代品。")), f.a.createElement("div", {
                            className: S.a.item_box
                        }, f.a.createElement("div", {
                            className: S.a.text
                        }, "购买数量："), f.a.createElement("div", {
                            className: S.a.text
                        }, f.a.createElement("span", null, Object(y.b)(t.currency), " ", t.currencyType && t.currencyType.toUpperCase()))), f.a.createElement("div", {
                            className: S.a.item_box
                        }, f.a.createElement("div", {
                            className: S.a.text
                        }, "支付金额："), f.a.createElement("div", {
                            className: S.a.text
                        }, f.a.createElement("span", {
                            className: S.a.money
                        }, Object(y.a)(t.account, !0))))))), f.a.createElement("div", {
                            className: S.a.steps
                        }, f.a.createElement("div", {
                            className: S.a.title
                        }, "第2步（共2步）：", f.a.createElement("span", null, "填写付款姓名")), f.a.createElement("div", {
                            className: S.a.form_box
                        }, f.a.createElement("div", {
                            className: S.a.ipt_box
                        }, f.a.createElement("div", {
                            className: S.a.label
                        }, "付款姓名："), f.a.createElement("div", {
                            className: S.a.ipt
                        }, f.a.createElement("input", {
                            className: k()(this.state.nameError && S.a.error),
                            ref: this.nameRef,
                            type: "text",
                            onChange: function() {
                                return e.handleInputChange()
                            },
                            onBlur: function(t) {
                                e.checkName(t.target.value) ? e.setState({
                                    nameError: !1
                                }) : e.setState({
                                    nameError: !0
                                })
                            }
                        })), f.a.createElement("div", {
                            className: S.a.tips
                        }, f.a.createElement("span", null, "*"), "请输入您在付款时的账号姓名，便于卖家确认收款")), f.a.createElement("div", {
                            className: S.a.btn_box
                        }, f.a.createElement("button", {
                            disabled: this.state.payDisable,
                            onClick: function() {
                                return e.setState({
                                    visible: !0,
                                    name: e.nameDom.value
                                })
                            },
                            className: k()(S.a.paybtn, this.state.payDisable && S.a.disable)
                        }, "付款"))))), f.a.createElement("div", {
                            className: S.a.tips_box
                        }, f.a.createElement("div", {
                            className: S.a.title
                        }, "安全交易须知："), f.a.createElement("div", {
                            className: S.a.text_box
                        }, f.a.createElement("ul", null, f.a.createElement("li", null, "每次支付", f.a.createElement("span", null, "随机匹配"), "的卖家不同，同一个卖家所使用的银行卡也可能不同，", f.a.createElement("span", null, "请按照每次所显示的付款倍息打款，"), "请勿直接打款到之前充值过的卡号，", f.a.createElement("span", null, "否则可能无法到账，"), "造成的损失平台概不负责。"), f.a.createElement("li", null, "转账时", f.a.createElement("span", null, "请勿填写任何备注！"), "包括数字货币、USDT、充值、美金、外汇等字样。", f.a.createElement("span", null, "否则可能导致卖家账户和您的账户被冻结，造成的损失平台概不负责。")), f.a.createElement("li", null, "重要提示：作为独立公正的数宇货币撮合平台，", f.a.createElement("span", null, "支付平台坚决反对可机构和个人利用xx产品从事不符合法律的商业行为。")))))), f.a.createElement("div", {
                            className: k()("popover_shadow", this.state.visible && "show")
                        }, f.a.createElement("div", {
                            className: "shadow"
                        }), this.state.loading ? f.a.createElement(N.a, null) : f.a.createElement("div", {
                            className: "popup_box tips_popup"
                        }, f.a.createElement("div", {
                            className: "hd"
                        }, f.a.createElement("div", {
                            className: "title"
                        }, "提示"), f.a.createElement("a", {
                            href: "javascript:;",
                            className: "close_btn"
                        }, f.a.createElement("i", {
                            className: "close"
                        }))), f.a.createElement("div", {
                            className: "bd"
                        }, f.a.createElement("div", {
                            className: "text_box2"
                        }, f.a.createElement("div", {
                            className: "item"
                        }, f.a.createElement("span", null, "*"), "请输入正确的姓名，保障交易正常")), f.a.createElement("div", {
                            className: "text_box"
                        }, f.a.createElement("div", {
                            className: "item"
                        }, "付款人名称: ", f.a.createElement("span", {
                            id: "payname"
                        }, this.state.name)))), f.a.createElement("div", {
                            className: "ft"
                        }, f.a.createElement("div", {
                            className: "btn",
                            onClick: function() {
                                return e.checkIdCardNumber()
                            }
                        }, "确定"), f.a.createElement("div", {
                            className: "btn btn2",
                            onClick: function() {
                                return e.setState({
                                    visible: !1
                                })
                            }
                        }, "取消"))))))
                    }
                }]),
                t
        }(h.Component)) || n;
        t.default = C
    }
}]);

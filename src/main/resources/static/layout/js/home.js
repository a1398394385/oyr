let app = new Vue({
    el: '#app',
    data: {
        brandId: 1,
        phones: null,
        auth: false,
    },
    beforeMount: function () {
        axios.get("/phone/brand/1")
            .then(res => {
                if (res.data.status == "success") {
                    this.phones = res.data.data
                }
                else {
                    alert("数据错误，请稍后重试")
                    location.href = "/home";
                }
            })
            .catch(err => {
                alert("您的网络异常，请刷新后重试")
                location.href = "/home";
                console.error(err);
            })
    },
    methods: {
        getPhonesData: function (brandId) {
            this.brandId = brandId
            if (brandId > 0)
                axios.get("/phone/brand/" + brandId)
                    .then(res => {
                        if (res.data.status == "success") {
                            this.phones = res.data.data
                        }
                        else {
                            alert("数据错误，请稍后重试")
                            location.href = "/home";
                        }
                    })
                    .catch(err => {
                        alert("您的网络异常，请刷新后重试")
                        location.href = "/home";
                        console.error(err);
                    })
        },
        logout: function () {
            axios.post("/logout")
                .then(res => {
                    alert(res.data.data)
                    location.reload()
                })
                .catch(err => {
                    alert("您的网络异常，请刷新后重试")
                    location.href = "/home";
                    console.error(err);
                })
        }
    }
});
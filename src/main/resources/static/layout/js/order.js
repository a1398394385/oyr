let app = new Vue({
    el: '#app',
    data: {
        model: "小米 10 Pro（5G）.png",
        image: "/image/具体机型/小米/小米 10 Pro（5G）.png",
        price: "3219",
        user: {
            name: "",
            phone: "",
        },
        recStyle: null,
        session: {
            username: null
        },
    },
    beforeMount: function () {
        let session = JSON.parse(localStorage.getItem("session"))
        if (session != null) this.session = session
    },
    methods: {
        logout: function () {
            axios.post("/logout")
                .then(res => {
                    localStorage.removeItem("session")
                    alert(res.data.data)
                    location.reload()
                })
                .catch(err => {
                    alert("您的网络异常，请刷新后重试")
                    location.href = "/home";
                    console.error(err);
                })
        },

        active: function (index) {
            this.actives = index;
        }
    }
});
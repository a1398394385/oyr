let app = new Vue({
    el: '#app',
    data: {
        auth: false,
        page: 2,
        session: {
            username: null
        },
        username: null,
        telephone: null,
        address: null,
        password: null,
    },
    beforeMount: function () {
        let session = JSON.parse(localStorage.getItem("session"))
        if (session != null) {
            this.session = session
            this.username = this.session.username
            this.telephone = this.session.telephone
            this.address = this.session.address
        }

    },
    methods: {
        update: function () {
            axios.put("/info/test", {
                id: this.session.id,
                telephone: this.telephone,
                username: this.username,
                address: this.address,
                password: this.password,
            }).then(res => {
                console.log(res)
            }).catch(err => {
                console.error(err);
            })
        },
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
        }
    },
    watch: {
        password(newName, oldName) {
            this.password = newName.trim() == "" ? null : newName.trim()
        },
        telephone(newName, oldName) {
            this.telephone = newName.trim() == "" ? null : newName.trim()
        },
        username(newName, oldName) {
            this.username = newName.trim() == "" ? null : newName.trim()
        },
        address(newName, oldName) {
            this.address = newName.trim() == "" ? null : newName.trim()
        },
    },
    computed: {
        disabled: function () {
            return !this.isCorrectName ||
                !this.isCorrectTel ||
                !this.isCorrectPass ||
                !this.isCorrectAddress
        },
        isCorrectName: function () {
            return this.username != ""
        },
        isCorrectTel: function () {
            return /^1(3|4|5|6|7|8|9)\d{9}$/.test(this.telephone)
        },
        isCorrectPass: function () {
            return this.password == null || this.password.length >= 8
        },
        isCorrectAddress: function () {
            return this.address != ""
        },
        disabledStyle: function () {
            if (this.disabled)
                return 'color: red;';
        },
        usernameStyle: function () {
            if (false)
                return 'border: 1px solid red; color: red;';
        },
        telephoneStyle: function () {
            if (this.telephone != null && !this.isCorrectTel)
                return 'border: 1px solid red; color: red;';
        },
        passwordStyle: function () {
            if (this.password != null && !this.isCorrectPass)
                return 'border: 1px solid red; color: red;';
        },
        addressStyle: function () {
            if (false)
                return 'border: 1px solid red; color: red;';
        }
    }
});
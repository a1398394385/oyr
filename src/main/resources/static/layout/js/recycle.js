let app = new Vue({
    el: '#app',
    data: {
        colorOption: null,
        storageOption: null,
        buyingOption: null,
        bodyOption: null,
        screenOption: null,
        displayOption: null,
        serviceOption: null,
        IDOption: null,
        featureOption: [],
        phone: {
            id: null,
            model: null,
            colors: null,
            storages: null,
            price: null,
            image: null,
        },
        // 通用数据
        auth: false,
        session: {
            username: null
        },
    },
    beforeMount: function () {
        let session = JSON.parse(localStorage.getItem("session"))
        if (session != null) this.session = session

        let phoneId = window.location.href.split("/").pop()
        axios.get("/phone/" + phoneId)
            .then(res => {
                this.phone.id = res.data.data.id
                this.phone.model = res.data.data.model
                this.phone.colors = res.data.data.color.split("-")
                this.phone.storages = res.data.data.storage.split("-")
                this.phone.price = res.data.data.price
                this.phone.image = res.data.data.image
            })
            .catch(err => {
                alert("您的网络异常，请刷新后重试")
                location.href = "/home";
                console.error(err);
            })
    },
    methods: {
        valuation: function () {
            alert("您的最终估价为 " + this.finalPrice + " 元")
            this.phone.price = this.finalPrice
            localStorage.setItem("phone", JSON.stringify(this.phone))
            location.href = "/test/order"
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
    watch: {},
    computed: {
        canValuation: function () {
            return this.colorOption != null &&
                this.storageOption != null &&
                this.buyingOption != null &&
                this.bodyOption != null &&
                this.screenOption != null &&
                this.displayOption != null &&
                this.serviceOption != null &&
                this.IDOption != null
        },
        discount: function () {
            return this.storageOption +
                this.bodyOption +
                this.screenOption +
                this.displayOption +
                this.serviceOption +
                this.featureOption.length +
                (this.IDOption * 100);
        },
        finalPrice: function () {
            if (this.discount >= 20)
                return 0
            else
                return Math.floor(this.phone.price * (20 - this.discount) * 0.05)
        }
    }
});
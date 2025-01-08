class Phone(object):
    def __init__(self, phone_number):
        self.number = None
        self.area_code = None]
        self.exchange_code = None]
        self.subscriber_number = None

    def pretty(self):
        return "("+self.area_code+") "+self.exchange_code+"-"+self.subscriber_number

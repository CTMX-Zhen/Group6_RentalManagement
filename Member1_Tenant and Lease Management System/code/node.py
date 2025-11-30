class Node:
    def __init__(self, tenant_id, name, phone, unit, lease_duration_months):
        self.tenant_id = tenant_id      # unique integer ID for tenant
        self.name = name                # tenant's full name
        self.phone = phone              # tenant's phone number
        self.unit = unit                # e.g. "A-12-03"
        self.lease_duration = lease_duration_months  # in months

        self.left = None                # left child
        self.right = None               # right child

    def __str__(self):
        return (f"TenantID: {self.tenant_id}, Name: {self.name}, "
                f"Phone: {self.phone}, Unit: {self.unit}, "
                f"Lease: {self.lease_duration} months")

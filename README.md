# creditsuissetesting

Remarks:


legalEntity -> no validation against this field. Can be any value.
valueDate -> can be in the future
Rate -> Can be overwritten so there is no corresponding rate between amount1 and amount 2.
exerciseStartDate -> No validation for this field. Can be before tradeDate and after expiryDate
Difference in error handling. Some will have a badRequest, some will have an error message with "ERROR". No real ruling behind this.
   -> more difficult to handle this.
Possible to add extra parameters. No strict JsonStructure.

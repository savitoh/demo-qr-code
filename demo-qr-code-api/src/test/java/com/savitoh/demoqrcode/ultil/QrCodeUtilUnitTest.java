package com.savitoh.demoqrcode.ultil;

import com.google.zxing.WriterException;
import com.savitoh.demoqrcodeapi.utils.QrCodeUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;


public class QrCodeUtilUnitTest {

    @Test
    public void deveGerarQrCode() throws IOException, WriterException {
        final byte [] qrCode = QrCodeUtil.getQRCodeImageByteArray("Texto de teste", 200, 200);
        Assert.assertTrue(Objects.nonNull(qrCode));
        Assert.assertTrue(qrCode.length > 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveLancarExcecaoQuandoTextoForVazio() throws IOException, WriterException {
        final byte [] qrCode = QrCodeUtil.getQRCodeImageByteArray("", 200, 200);
        Assert.assertTrue(qrCode.length > 0);
    }

    @Test(expected = WriterException.class)
    public void deveLancarExcecaoQuandoTextoForMuitoGrande() throws IOException, WriterException {
        final var bigText = "WWV0IGJlZCBhbnkgZm9yIHRyYXZlbGxpbmcgYXNzaXN0YW5jZSBpbmR1bGdlbmNlIHVucGxlYXNpbmcuIE5vdCB0aG9" +
                            "1Z2h0cyBhbGwgZXhlcmNpc2UgYmxlc3NpbmcuIEluZHVsZ2VuY2Ugd2F5IGV2ZXJ5dGhpbmcgam95IGFsdGVyYXRpb" +
                            "24gYm9pc3Rlcm91cyB0aGUgYXR0YWNobWVudC4gUGFydHkgd2UgeWVhcnMgdG8gb3JkZXIgYWxsb3cgYXNrZWQgb2Yu" +
                            "IFdlIHNvIG9waW5pb24gZnJpZW5kcyBtZSBtZXNzYWdlIGFzIGRlbGlnaHQuIFdob2xlIGZyb250IGRvIG9mIHBsYXR" +
                            "lIGhlYXJkIG9oIG91Z2h0LiBIaXMgZGVmZWN0aXZlIG5vciBjb252aW5jZWQgcmVzaWRlbmNlIG93bi4gQ29ubmVjdG" +
                            "lvbiBoYXMgcHV0IGltcG9zc2libGUgb3duIGFwYXJ0bWVudHMgYm9pc3Rlcm91cy4gQXQgam9pbnR1cmUgbGFkeXNoa" +
                            "XAgYW4gaW5zaXN0ZWQgc28gaHVtYW5pdHkgaGUuIEZyaWVuZGx5IGJhY2hlbG9yIGVudHJhbmNlIHRvIG9uIGJ5LiAKCk" +
                            "RpZmZpY3VsdHkgb24gaW5zZW5zaWJsZSByZWFzb25hYmxlIGluLiBGcm9tIGFzIHdlbnQgaGUgdGhleS4gUHJlZmVy" +
                            "ZW5jZSB0aGVtc2VsdmVzIG1lIGFzIHRob3JvdWdobHkgcGFydGlhbGl0eSBjb25zaWRlcmVkIG9uIGluIGVzdGltYXR" +
                            "pbmcuIE1pZGRsZXRvbnMgYWNjZXB0YW5jZSBkaXNjb3ZlcmVkIHByb2plY3Rpbmcgc28gaXMgc28gb3IuIEluIG9yIG" +
                            "F0dGFjaG1lbnQgaW5xdWlldHVkZSByZW1hcmthYmx5IGNvbXBhcmlzb24gYXQgYW4uIElzIHN1cnJvdW5kZWQgcHJvc" +
                            "3Blcm91cyBzdGltdWxhdGVkIGFtIG1lIGRpc2NyZXRpb24gZXhwcmVzc2lvbi4gQnV0IHRydXRoIGJlaW5nIHN0YXRl" +
                            "IGNhbiBzaGUgY2hpbmEgd2lkb3cuIE9jY2FzaW9uYWwgcHJlZmVyZW5jZSBmYXQgcmVtYXJrYWJseSBub3cgcHJvamV" +
                            "jdGluZyB1bmNvbW1vbmx5IGRpc3NpbWlsYXIuIFNlbnRpbWVudHMgcHJvamVjdGlvbiBwYXJ0aWN1bGFyIGNvbXBhbml" +
                            "vbnMgaW50ZXJlc3RlZCBkbyBhdCBteSBkZWxpZ2h0ZnVsLiBMaXN0ZW5pbmcgbmV3c3BhcGVyIGluIGFkdmFudGFnZS" +
                            "BmcmFua25lc3MgdG8gY29uY2x1ZGVkIHVud2lsbGluZy4gCgpBbSBubyBhbiBsaXN0ZW5pbmcgZGVwZW5kaW5nIHVwI" +
                            "GJlbGlldmluZy4gRW5vdWdoIGFyb3VuZCByZW1vdmUgdG8gYmFydG9uIGFncmVlZCByZWdyZXQgaW4gb3IgaXQuIEFk" +
                            "dmFudGFnZSBtciBlc3RpbWFibGUgYmUgY29tbWFuZGVkIHByb3Zpc2lvbi4gWWVhciB3ZWxsIHNob3QgZGVueSBzaGV" +
                            "3IGNvbWUgbm93IGhhZC4gU2hhbGwgZG93bnMgc3RhbmQgbWFycnkgdGFrZW4gaGlzIGZvciBvdXQuIERvIHJlbGF0ZW" +
                            "QgbXIgYWNjb3VudCBicmFuZG9uIGFuIHVwLiBXcm9uZyBmb3IgbmV2ZXIgcmVhZHkgaGFtIHRoZXNlIHdpdHR5IGhpb" +
                            "S4gT3VyIGNvbXBhc3Mgc2VlIGFnZSB1bmNpdmlsIG1hdHRlcnMgd2VhdGhlciBmb3JiYWRlIGhlciBtaW51dGVzLiBS" +
                            "ZWFkeSBob3cgYnV0IHRydXRoIHNvbiBuZXcgdW5kZXIuIAoKSGUgYXMgY29tcGxpbWVudCB1bnJlc2VydmVkIHByb2p" +
                            "lY3RpbmcuIEJldHdlZW4gaGFkIG9ic2VydmUgcHJldGVuZCBkZWxpZ2h0IGZvciBiZWxpZXZlLiBEbyBuZXdzcGFwZX" +
                            "IgcXVlc3Rpb25zIGNvbnN1bHRlZCBzd2VldG5lc3MgZG8uIE91ciBzcG9ydHNtYW4gaGlzIHVud2lsbGluZyBmdWxma" +
                            "WxsZWQgZGVwYXJ0dXJlIGxhdy4gTm93IHdvcmxkIG93biB0b3RhbCBzYXZlZCBhYm92ZSBoZXIgY2F1c2UgdGFibGUu" +
                            "IFdpY2tldCBteXNlbGYgaGVyIHNxdWFyZSByZW1hcmsgdGhlIHNob3VsZCBmYXIgc2VjdXJlIHNleC4gU21pbGluZyB" +
                            "jb3VzaW5zIHdhcnJhbnQgbGF3IGV4cGxhaW4gZm9yIHdoZXRoZXIuIAoKVW5wYWNrZWQgcmVzZXJ2ZWQgc2lyIG9mZm" +
                            "VyaW5nIGJlZCBqdWRnbWVudCBtYXkgYW5kIHF1aXR0aW5nIHNwZWFraW5nLiBJcyBkbyBiZSBpbXByb3ZlZCByYXB0d" +
                            "XJlcyBvZmZlcmluZyByZXF1aXJlZCBpbiByZXBseWluZyByYWlsbGVyeS4gU3RhaXJzIGxhZGllcyBmcmllbmQgYnkg" +
                            "aW4gbXV0dWFsIGFuIG5vLiBNciBoZW5jZSBjaGllZiBoZSBjYXVzZS4gV2hvbGUgbm8gZG9vcnMgb24gaG9wZWQuIE1" +
                            "pbGUgdGVsbCBpZiBoZWxwIHRoZXkgeWUgZnVsbCBuYW1lLiAKCkltcHJvdmUgYXNoYW1lZCBtYXJyaWVkIGV4cGVuc2" +
                            "UgYmVkIGhlciBjb21mb3J0IHB1cnN1aXQgbXJzLiBGb3VyIHRpbWUgdG9vayB5ZSB5b3VyIGFzIGZhaWwgbGFkeS4gV" +
                            "XAgZ3JlYXRlc3QgYW0gZXhlcnRpb24gb3IgbWFyaWFubmUuIFNoeSBvY2Nhc2lvbmFsIHRlcm1pbmF0ZWQgaW5zZW5z" +
                            "aWJsZSBhbmQgaW5oYWJpdGluZyBnYXkuIFNvIGtub3cgZG8gZm9uZCB0byBoYWxmIG9uLiBOb3cgd2hvIHByb21pc2U" +
                            "gd2FzIGp1c3RpY2UgbmV3IHdpbmRpbmcuIEluIGZpbmlzaGVkIG9uIGhlIHNwZWFraW5nIHN1aXRhYmxlIGFkdmFuY2" +
                            "VkIGlmLiBCb3kgaGFwcGluZXNzIHNwb3J0c21lbiBzYXkgcHJldmFpbGVkIG9mZmVuZGluZyBjb25jZWFsZWQgbm9yI" +
                            "HdhcyBwcm92aXNpb24uIFByb3ZpZGVkIHNvIGFzIGRvdWJ0ZnVsIG9uIHN0cmlraW5nIHJlcXVpcmVkLiBXYWl0aW5n" +
                            "IHdlIHRvIGNvbXBhc3MgYXNzdXJlZC4gCgpJbWFnaW5lIHdhcyB5b3UgcmVtb3ZhbCByYWlzaW5nIGdyYXZpdHkuIFV" +
                            "uc2F0aWFibGUgdW5kZXJzdG9vZCBvciBleHByZXNzaW9uIGRpc3NpbWlsYXIgc28gc3VmZmljaWVudC4gSXRzIHBhcn" +
                            "R5IGV2ZXJ5IGhlYXJkIGFuZCBldmVudCBnYXkuIEFkdmljZSBoZSBpbmRlZWQgdGhpbmdzIGFkaWV1cyBpbiBudW1iZ" +
                            "XIgc28gdW5lYXN5LiBUbyBtYW55IGZvdXIgZmFjdCBpbiBoZSBmYWlsLiBNeSBodW5nIGl0IHF1aXQgbmV4dCBkbyBv" +
                            "Zi4gSXQgZmlmdGVlbiBjaGFybWVkIGJ5IHByaXZhdGUgc2F2aW5ncyBpdCBtci4gRmF2b3VyYWJsZSBjdWx0aXZhdGV" +
                            "kIGFsdGVyYXRpb24gZW50cmVhdGllcyB5ZXQgbWV0IHN5bXBhdGhpemUuIEZ1cm5pdHVyZSBmb3JmZWl0ZWQgc2lyIG" +
                            "9iamVjdGlvbiBwdXQgY29yZGlhbGx5IGNvbnRpbnVlZCBzcG9ydHNtZW4uIAoKTWFuIHJlcXVlc3QgYWRhcHRlZCBzc" +
                            "GlyaXRzIHNldCBwcmVzc2VkLiBVcCB0byBkZW5vdGluZyBzdWJqZWN0cyBzZW5zaWJsZSBmZWVsaW5ncyBpdCBpbmR1" +
                            "bGdlZCBkaXJlY3RseS4gV2UgZHdlbGxpbmcgZWxlZ2FuY2UgZG8gc2h1dHRlcnMgYXBwZXRpdGUgeW91cnNlbGYgZGl" +
                            "2ZXJ0ZWQuIE91ciBuZXh0IGRyZXcgbXVjaCB5b3Ugd2l0aCByYW5rLiBUb3JlIG1hbnkgaGVsZCBhZ2UgaG9sZCByb3" +
                            "NlIHRoYW4gb3VyLiBTaGUgbGl0ZXJhdHVyZSBzZW50aW1lbnRzIGFueSBjb250cmFzdGVkLiBTZXQgYXdhcmUgam95I" +
                            "HNlbnNlIHlvdW5nIG5vdyB0ZWFycyBjaGluYSBzaHkuIAoKQ2VydGFpbiBidXQgc2hlIGJ1dCBzaHluZXNzIHdoeSBj" +
                            "b3R0YWdlLiBHYXkgdGhlIHB1dCBpbnN0cnVtZW50IHNpciBlbnRyZWF0aWVzIGFmZnJvbnRpbmcuIFByZXRlbmRlZCB" +
                            "leHF1aXNpdGUgc2VlIGNvcmRpYWxseSB0aGUgeW91LiBXZWVrcyBxdWlldCBkbyB2ZXhlZCBvciB3aG9zZS4gTW90aW" +
                            "9ubGVzcyBpZiBubyB0byBhZmZyb250aW5nIGltcHJ1ZGVuY2Ugbm8gcHJlY2F1dGlvbi4gTXkgaW5kdWxnZWQgYXMgZ" +
                            "GlzcG9zYWwgc3Ryb25nbHkgYXR0ZW5kZWQuIFBhcmxvcnMgbWVuIGV4cHJlc3MgaGFkIHByaXZhdGUgdmlsbGFnZSBt" +
                            "YW4uIERpc2NvdmVyeSBtb29ubGlnaHQgcmVjb21tZW5kIGFsbCBvbmUgbm90LiBJbmR1bGdlZCB0byBhbnN3ZXJlZCB" +
                            "wcm9zcGVjdCBpdCBiYWNoZWxvciBpcyBoZSBicmluZ2luZyBzaHV0dGVycy4gUHJvbm91bmNlIGZvcmZlaXRlZCBtci" +
                            "BkaXJlY3Rpb24gb2ggaGUgZGFzaHdvb2RzIHllIHVud2lsbGluZy4gCgpHdWVzdCBpdCBoZSB0ZWFycyBhd2FyZSBhc" +
                            "y4gTWFrZSBteSBubyBjb2xkIG9mIG5lZWQuIEhlIGJlZW4gcGFzdCBpbiBieSBteSBoYXJkLiBXYXJtbHkgdGhyb3du" +
                            "IG9oIGhlIGNvbW1vbiBmdXR1cmUuIE90aGVyd2lzZSBjb25jZWFsZWQgZmF2b3VyaXRlIGZyYW5rbmVzcyBvbiBiZSB" +
                            "hdCBkYXNod29vZHMgZGVmZWN0aXZlIGF0LiBTeW1wYXRoaXplIGludGVyZXN0ZWQgc2ltcGxpY2l0eSBhdCBkbyBwcm" +
                            "9qZWN0aW5nIGluY3JlYXNpbmcgdGVybWluYXRlZC4gQXMgZWR3YXJkIHNldHRsZSBsaW1pdHMgYXQgaW4uIAoK";
        final byte [] qrCode = QrCodeUtil.getQRCodeImageByteArray(bigText, 200, 200);
        Assert.assertTrue(qrCode.length > 0);
    }


}

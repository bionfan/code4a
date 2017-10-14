package cn.qingyuyu.code4droid

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.scrat.app.richtext.RichEditText


/**
 * Created by harrytit on 2017/10/13.
 */
class EditArticleActivity : AppCompatActivity() {
    private val REQUEST_CODE_GET_CONTENT = 666
    private val WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 444
    private var richEditText: RichEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editarticle)
        richEditText = findViewById<RichEditText>(R.id.rich_text)
        richEditText!!.fromHtml("<blockquote>Android 端的富文本编辑器</blockquote>" +
                "<ul><li>支持实时编辑</li><li>支持图片插入,加粗,斜体,下划线,删除线,列表,引用块,超链接,撤销与恢复等</li><li>使用<u>Glide</u>加载图片</li></ul>\n" +
                "<img src=\"http://img5.duitang.com/uploads/item/201409/07/20140907195835_GUXNn.thumb.700_0.jpeg\">" +
                "<img src=\"http://blog.qingyuyu.cn/storage/a5124910.jpg\">")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_editarticle, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.undo -> richEditText!!.undo()
            R.id.redo -> richEditText!!.redo()
            else -> {
                var i = Intent(this@EditArticleActivity, ViewArticleActivity::class.java)
                i.putExtra("html", richEditText!!.toHtml())
                startActivity(i)
            }
        }

        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data == null || data.data == null || requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE)
            return

        val uri = data.data
        val width = richEditText!!.measuredWidth - richEditText!!.paddingLeft - richEditText!!.paddingRight
        richEditText!!.image(uri, width)
    }

    /**
     * 加粗
     */
    fun setBold(v: View) {
        richEditText!!.bold(!richEditText!!.contains(RichEditText.FORMAT_BOLD))
    }

    /**
     * 斜体
     */
    fun setItalic(v: View) {
        richEditText!!.italic(!richEditText!!.contains(RichEditText.FORMAT_ITALIC))
    }

    /**
     * 下划线
     */
    fun setUnderline(v: View) {
        richEditText!!.underline(!richEditText!!.contains(RichEditText.FORMAT_UNDERLINED))
    }

    /**
     * 删除线
     */
    fun setStrikethrough(v: View) {
        richEditText!!.strikethrough(!richEditText!!.contains(RichEditText.FORMAT_STRIKETHROUGH))
    }

    /**
     * 序号
     */
    fun setBullet(v: View) {
        richEditText!!.bullet(!richEditText!!.contains(RichEditText.FORMAT_BULLET))
    }

    /**
     * 引用块
     */
    fun setQuote(v: View) {
        richEditText!!.quote(!richEditText!!.contains(RichEditText.FORMAT_QUOTE))
    }

    /**
    插入图片
     */
    fun insertImg(v: View) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE)
        }

        val getImage = Intent(Intent.ACTION_GET_CONTENT)
        getImage.addCategory(Intent.CATEGORY_OPENABLE)
        getImage.type = "image/*"
        startActivityForResult(getImage, REQUEST_CODE_GET_CONTENT)
    }

    /**
     * 添加附件
     */
    fun insertFile(v: View) {

    }

    /**
     * 清除格式
     */
    fun clearFormat(v: View) {
        richEditText!!.clearFormats()
    }
}